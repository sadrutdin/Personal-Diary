package com.zaynukov.dev.angeldiary.service.note;

import com.zaynukov.dev.angeldiary.model.ItemOfNoteList;
import com.zaynukov.dev.angeldiary.service.DiaryConstants;
import com.zaynukov.dev.angeldiary.service.DiaryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"SqlNoDataSourceInspection", "SqlResolve"})
@Service
class NoteServiceImpl implements NoteService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<ItemOfNoteList> allNotes(String login, String password) throws SQLException {
        Connection connection = DiaryUtils.getConnection(login, password);
        String sql = "select * from notes order by id desc";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        List<ItemOfNoteList> result = getNoteList(rs);
        connection.close();
        return result;
    }

    @Override
    public List<ItemOfNoteList> notesWithFilter(String login, String password, String date1, String date2) throws SQLException {

        if (true) {
            logger.info("----------");
            logger.info("login = " + login);
            logger.info("password = " + password);
            logger.info("date1 = " + date1);
            logger.info("date2 = " + date2);
            return Collections.emptyList();
        }

        if (date1 == null && date2 == null) return allNotes(login, password);

        // TODO
        Timestamp d1 = null, d2 = null;

        Connection connection = DiaryUtils.getConnection(login, password);
        String sql = "select * from notes where create_date between ? and ?2 order by id desc";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setTimestamp(1, d1);
        st.setTimestamp(2, d2);

        ResultSet rs = st.executeQuery();
        List<ItemOfNoteList> result = getNoteList(rs);
        connection.close();

        return result;
    }

    private List<ItemOfNoteList> getNoteList(ResultSet rs) throws SQLException {
        List<ItemOfNoteList> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(DiaryConstants.TableColumnName.ID);

            String title = rs.getString(DiaryConstants.TableColumnName.TITLE);

            LocalDateTime dateTime = LocalDateTime.ofInstant(rs.getTimestamp(
                    DiaryConstants.TableColumnName.CREATE_DATE).toInstant(), ZoneId.systemDefault());

            String changeHistory = rs.getString(DiaryConstants.TableColumnName.CHANGE_HISTORY);

            list.add(new ItemOfNoteList(id, title, dateTime, changeHistory));
        }
        return list;
    }


}
