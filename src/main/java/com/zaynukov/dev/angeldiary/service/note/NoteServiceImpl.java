package com.zaynukov.dev.angeldiary.service.note;

import com.zaynukov.dev.angeldiary.exception.DiaryParamIsBadFormat;
import com.zaynukov.dev.angeldiary.model.ItemOfNoteListDTO;
import com.zaynukov.dev.angeldiary.model.NoteDTO;
import com.zaynukov.dev.angeldiary.model.TimestampPair;
import com.zaynukov.dev.angeldiary.model.sql.TableChanges;
import com.zaynukov.dev.angeldiary.model.sql.TableNotes;
import com.zaynukov.dev.angeldiary.service.DiaryUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.zaynukov.dev.angeldiary.service.DiaryUtils.getCurrentTimestamp;

@Service
class NoteServiceImpl implements NoteService {

//    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public NoteDTO getNote(Authentication user, int noteId) throws SQLException {
        String login = user.getName();
        String password = user.getCredentials().toString();

        String title, noteText;
        LocalDateTime createDateTime;
        List<LocalDateTime> changeList = new ArrayList<>();

        try (Connection connection = DiaryUtils.getConnection(login, password)) {
            try (PreparedStatement ps = connection.prepareStatement(TableNotes.SELECT_ONLY)) {
                ps.setInt(1, noteId);
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    title = rs.getString(TableNotes.Column.TITLE.name());
                    noteText = rs.getString(TableNotes.Column.NOTE_TEXT.name());
                    createDateTime = rs.getTimestamp(TableNotes.Column.CREATE_DATE.name()).toLocalDateTime();
                }
            }
            try (PreparedStatement ps = connection.prepareStatement(TableChanges.GET_ALL)) {
                ps.setInt(1, noteId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next())
                        changeList.add(rs.getTimestamp(1).toLocalDateTime());
                }
            }
        }

        return new NoteDTO(
                noteId,
                title,
                noteText,
                createDateTime,
                changeList
        );
    }

    @Override
    public List<ItemOfNoteListDTO> allNotes(Authentication user) throws SQLException {
        String login = user.getName();
        String password = user.getCredentials().toString();

        List<ItemOfNoteListDTO> list = new ArrayList<>();
        try (Connection connection = DiaryUtils.getConnection(login, password)) {
            try (Statement st = connection.createStatement()) {
                try (ResultSet rs = st.executeQuery(TableNotes.SELECT_ALL_DESC)) {
                    addDTOs(list, rs);
                }
            }
        }

        return list;
    }

    @Override
    public List<ItemOfNoteListDTO> notesWithFilter(Authentication user, String dateRange, String search) throws SQLException {

        if (dateRange == null && search == null) return allNotes(user);

        String login = user.getName();
        String password = user.getCredentials().toString();

        TimestampPair timestampPair = DiaryUtils.parseDateRange(dateRange);
        Timestamp d1, d2;
        d1 = timestampPair.getBegin();
        d2 = timestampPair.getEnd();


        boolean withSearch = search != null && !search.isEmpty();
        boolean withDateRange = d1 != null && d2 != null;
        boolean withSearchAndDateRange = withSearch && withDateRange;

        String searchSqlText;

        List<ItemOfNoteListDTO> list = new ArrayList<>();
        try (Connection connection = DiaryUtils.getConnection(login, password)) {
            if (withSearchAndDateRange) {
                searchSqlText = DiaryUtils.toSqlLikeFormat(search);
                try (PreparedStatement st = connection.prepareStatement(TableNotes.SELECT_ALL_DESC_WITH_SEARCH_DATE_RANGE)) {
                    st.setTimestamp(1, d1);
                    st.setTimestamp(2, d2);
                    st.setString(3, searchSqlText);
                    st.setString(4, searchSqlText);

                    addDTOs(list, st);
                }
            } else if (withSearch) {
                searchSqlText = DiaryUtils.toSqlLikeFormat(search);
                try (PreparedStatement st = connection.prepareStatement(TableNotes.SELECT_ALL_DESC_WITH_SEARCH)) {
                    st.setString(1, searchSqlText);
                    st.setString(2, searchSqlText);
                    addDTOs(list, st);
                }
            } else if (withDateRange) {
                try (PreparedStatement st = connection.prepareStatement(TableNotes.SELECT_ALL_DESC_WITH_DATE_RANGE)) {
                    st.setTimestamp(1, d1);
                    st.setTimestamp(2, d2);
                    addDTOs(list, st);
                }
            }
        }
        return list;
    }

    private void addDTOs(List<ItemOfNoteListDTO> target, PreparedStatement st) throws SQLException {
        try (ResultSet rs = st.executeQuery()) {
            addDTOs(target, rs);
        }
    }

    private void addDTOs(List<ItemOfNoteListDTO> target, ResultSet rs) throws SQLException {
        while (rs.next()) {
            int id = rs.getInt(1);
            String title = rs.getString(2);

            Timestamp create = rs.getTimestamp(3);
            Timestamp lastChange = rs.getTimestamp(4);

            target.add(new ItemOfNoteListDTO(
                    id,
                    title,
                    create.toLocalDateTime(),
                    lastChange != null ? lastChange.toLocalDateTime() : null
            ));
        }
    }

    @Override
    public void saveNote(Authentication user, String title, String noteText) throws SQLException, DiaryParamIsBadFormat {
        if (title == null || noteText == null || title.isEmpty() || noteText.isEmpty())
            throw new DiaryParamIsBadFormat("Параметры 'title' и 'noteText' недопустимые: " +
                    "title = '" + title + "'; noteText = '" + noteText + "'.");

        String login = user.getName();
        String password = user.getCredentials().toString();

        try (Connection connection = DiaryUtils.getConnection(login, password)) {
            try (PreparedStatement ps = connection.prepareStatement(TableNotes.INSERT)) {
                ps.setString(1, title);
                ps.setString(2, noteText);
                ps.setTimestamp(3, getCurrentTimestamp());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void editNote(Authentication user, int noteId, String newTitle, String newText) throws SQLException {
        String login = user.getName();
        String password = user.getCredentials().toString();

        try (Connection connection = DiaryUtils.getConnection(login, password)) {
            try (PreparedStatement ps = connection.prepareStatement(TableNotes.UPDATE)) {
                ps.setString(1, newTitle);
                ps.setString(2, newText);
                ps.setInt(3, noteId);
                ps.execute();
            }

            try (PreparedStatement ps = connection.prepareStatement(TableChanges.INSERT)) {
                ps.setInt(1, noteId);
                ps.setTimestamp(2, getCurrentTimestamp());
                ps.execute();
            }
        }
    }

    @Override
    public void deleteNote(Authentication user, int noteId) throws SQLException {
        String login = user.getName();
        String password = user.getCredentials().toString();

        try (Connection connection = DiaryUtils.getConnection(login, password)) {
            try (PreparedStatement ps = connection.prepareStatement(TableNotes.DELETE)) {
                ps.setInt(1, noteId);
                ps.executeUpdate();
            }
        }
    }

}
