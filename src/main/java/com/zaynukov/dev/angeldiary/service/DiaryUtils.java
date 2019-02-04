package com.zaynukov.dev.angeldiary.service;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DiaryUtils {
    private DiaryUtils() {
    }

    public static Connection getConnection(String login, String pass) throws SQLException {
        String url = "jdbc:h2:~/" + login;
        return DriverManager.getConnection(url, login, pass);
    }

    public static boolean existDiary(String login) {
        String path = System.getProperty("user.home") + '/' + login + ".mv.db";
        return new File(path).exists();
    }
}
