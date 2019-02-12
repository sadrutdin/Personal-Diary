package com.zaynukov.dev.angeldiary.service.note;

import com.zaynukov.dev.angeldiary.exception.DiaryParamIsBadFormat;
import com.zaynukov.dev.angeldiary.model.ItemOfNoteListDTO;
import com.zaynukov.dev.angeldiary.model.NoteDTO;
import org.springframework.security.core.Authentication;

import java.sql.SQLException;
import java.util.List;

public interface NoteService {
    NoteDTO getNote(Authentication user, int id) throws SQLException;

    List<ItemOfNoteListDTO> allNotes(Authentication user) throws SQLException;

    void saveNote(Authentication user, String title, String noteText) throws SQLException, DiaryParamIsBadFormat;

    void editNote(Authentication user, int noteId, String newTitle, String newText) throws SQLException;

    void deleteNote(Authentication user, int noteId) throws SQLException;

    List<ItemOfNoteListDTO> notesWithFilter(Authentication user, String dateRange, String search) throws SQLException;
}
