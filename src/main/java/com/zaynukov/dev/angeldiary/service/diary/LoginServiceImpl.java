package com.zaynukov.dev.angeldiary.service.diary;

import com.zaynukov.dev.angeldiary.exception.DiaryIsNotCreateException;
import com.zaynukov.dev.angeldiary.exception.DiaryIsNotExistsException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.*;

@SuppressWarnings("SqlNoDataSourceInspection")
@Service
class LoginServiceImpl implements LoginService {
    public LoginServiceImpl() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public boolean existDiary(String login) {
        String path = System.getProperty("home.user") + '/' + login + ".mv.db";
        return new File(path).exists();
    }

    @Override
    public void createDiary(String login, String pass) throws DiaryIsNotCreateException {
        if (existDiary(login))
            throw new DiaryIsNotCreateException("Ошибка при создании дневника.\nlogin: " + login);
        String createTableSQL;
        try {
            Connection connection = getConnection(login, pass);
            Statement st = connection.createStatement();
            createTableSQL = "create table notes(id int auto_increment unique primary key not null, title varchar(60) not null, note_text varchar(32768) not null, date_time timestamp not null)";
            st.execute(createTableSQL);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean match(String login, String pass) throws DiaryIsNotExistsException {
        if (!existDiary(login))
            throw new DiaryIsNotExistsException("Дневника не существует");

        try {
            Connection connection = getConnection(login, pass);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select 1");
            boolean r = rs.next();
            connection.close();
            return r;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Connection getConnection(String login, String pass) throws SQLException {
        String url = "jdbc:h2:~/" + login;
        return DriverManager.getConnection(url, login, pass);
    }

}
