package com.zaynukov.dev.angeldiary.service.diary;

import com.zaynukov.dev.angeldiary.exception.DiaryIsExistException;
import com.zaynukov.dev.angeldiary.exception.DiaryIsNotExistsException;
import com.zaynukov.dev.angeldiary.service.DiaryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.zaynukov.dev.angeldiary.service.DiaryConstants.INIT_NEW_DIARY;
import static com.zaynukov.dev.angeldiary.service.DiaryConstants.TEST_AUTH_DB_QUERY;

@Service
class LoginServiceImpl implements LoginService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public LoginServiceImpl() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Не найден класс драйвера базы данных H2", e);
            System.exit(-1);
        }
    }

    @Override
    public void createDiary(String login, String pass) throws DiaryIsExistException, SQLException {
        if (DiaryUtils.existDiary(login))
            throw new DiaryIsExistException("Дневник не создан.\nlogin: " + login);

        Connection connection = DiaryUtils.getConnection(login, pass);
        Statement st = connection.createStatement();
        st.execute(INIT_NEW_DIARY);
        connection.close();

    }

    @Override
    public boolean match(String login, String pass) throws DiaryIsNotExistsException {
        if (!DiaryUtils.existDiary(login))
            throw new DiaryIsNotExistsException("Дневника не существует");

        try {
            Connection connection = DiaryUtils.getConnection(login, pass);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(TEST_AUTH_DB_QUERY);
            boolean r = rs.next();
            connection.close();
            return r;
        } catch (SQLException e) {
            logger.error("Ошибка при работе с БД.", e);
            System.exit(-1);
            return false;
        }
    }

}
