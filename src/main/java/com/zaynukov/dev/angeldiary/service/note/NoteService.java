package com.zaynukov.dev.angeldiary.service.note;

import com.zaynukov.dev.angeldiary.model.ItemOfNoteList;

import java.sql.SQLException;
import java.util.List;

public interface NoteService {
    List<ItemOfNoteList> allNotes(String login, String password) throws SQLException;

    List<ItemOfNoteList> notesWithFilter(String login, String password, String date1, String date2) throws SQLException;
}
