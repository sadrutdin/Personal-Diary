package com.zaynukov.dev.angeldiary.service.diary;

import com.zaynukov.dev.angeldiary.exception.DiaryNotFoundFile;
import com.zaynukov.dev.angeldiary.exception.DiaryUnknownException;

import java.time.LocalDateTime;
import java.util.List;

public interface NoteService {
    List<LocalDateTime> datesOfWriteNotes(String password, String ddMMyyyy_A, String ddMMyyyy_B) throws DiaryNotFoundFile, DiaryUnknownException;

    List<LocalDateTime> datesOfWriteNotes(String password) throws DiaryNotFoundFile, DiaryUnknownException;

}
