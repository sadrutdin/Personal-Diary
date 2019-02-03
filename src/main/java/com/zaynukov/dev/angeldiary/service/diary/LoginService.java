package com.zaynukov.dev.angeldiary.service.diary;

import com.zaynukov.dev.angeldiary.exception.DiaryIsNotCreateException;
import com.zaynukov.dev.angeldiary.exception.DiaryIsNotExistsException;

public interface LoginService {
    boolean existDiary(String login);

    void createDiary(String login, String pass) throws DiaryIsNotCreateException;

    boolean match(String login, String pass) throws DiaryIsNotExistsException;
}
