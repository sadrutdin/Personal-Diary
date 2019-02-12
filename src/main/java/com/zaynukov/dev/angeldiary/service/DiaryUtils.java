package com.zaynukov.dev.angeldiary.service;

import com.zaynukov.dev.angeldiary.exception.DiaryParamIsBadFormat;
import com.zaynukov.dev.angeldiary.model.TimestampPair;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DiaryUtils {
    private DiaryUtils() {
    }

    public static Connection getConnection(String login, String pass) throws SQLException {
        return DriverManager.getConnection(
                "jdbc:h2:~/" + login,
                login,
                pass
        );
    }

    public static boolean existDiary(@NotNull String login) {
        String path = System.getProperty("user.home") + '/' + login + ".mv.db";
        return new File(path).exists();
    }


    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:SS");

    public static TimestampPair parseDateRange(String dateRange) throws DiaryParamIsBadFormat {
        try {
            String[] split = dateRange.split(" - ");
            String s1 = split[0];
            String s2 = split[1];

            LocalDate ldt1 = LocalDate.parse(s1, dateFormatter);
            LocalDate ldt2 = LocalDate.parse(s2, dateFormatter).plusDays(1);

            return new TimestampPair(
                    Timestamp.valueOf(ldt1.atTime(0, 0)),
                    Timestamp.valueOf(ldt2.atTime(0, 0))
            );
        } catch (Exception e) {
            throw new DiaryParamIsBadFormat("dateRange = " + dateRange, e);
        }
    }

    public static String formatDate(LocalDateTime dateTime) {
        return formatDate(dateTime.toLocalDate());
    }

    public static String formatDate(LocalDate date) {
        return date.format(dateFormatter);
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(dateTimeFormatter);
    }
}
