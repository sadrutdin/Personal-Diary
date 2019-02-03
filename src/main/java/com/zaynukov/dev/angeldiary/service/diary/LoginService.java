package com.zaynukov.dev.angeldiary.service.diary;

import com.zaynukov.dev.angeldiary.exception.DiaryIsExistException;
import com.zaynukov.dev.angeldiary.exception.DiaryIsNotExistsException;

import java.sql.SQLException;

public interface LoginService {
    void createDiary(String login, String pass) throws DiaryIsExistException, SQLException;

    boolean match(String login, String pass) throws DiaryIsNotExistsException;
}
