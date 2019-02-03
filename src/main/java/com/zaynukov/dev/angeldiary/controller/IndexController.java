package com.zaynukov.dev.angeldiary.controller;

import com.zaynukov.dev.angeldiary.exception.DiaryIsExistException;
import com.zaynukov.dev.angeldiary.service.diary.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.sql.SQLException;

@Controller
@RequestMapping
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("")
    public String index() {
        return "redirect:/sign-in";
    }

    @GetMapping("/sign-in")
    public String signIn() {
        return "sign-in";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "sign-up";
    }

    @GetMapping("/note-list")
    public String noteList() {
        return "note-list";
    }

    @Inject
    private LoginService loginService;

    @PostMapping("/create-diary")
    public ModelAndView createDiary(@RequestParam String login,
                                    @RequestParam String pass) {
        String status;
        try {
            loginService.createDiary(login, pass);
            status = "OK";
        } catch (DiaryIsExistException e) {
            logger.error("Дневник с таким логином существует.", e);
            status = "DiaryIsExistException";
        } catch (SQLException e) {
            logger.error("Ошибка при работе с сервисом базы данных - не создан новый дневник", e);
            status = "SQLException";
        }

        return new ModelAndView("redirect:/sign-in")
                .addObject("status", status);

    }


}

