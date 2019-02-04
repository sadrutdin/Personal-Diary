package com.zaynukov.dev.angeldiary.controller;

import com.zaynukov.dev.angeldiary.exception.DiaryIsExistException;
import com.zaynukov.dev.angeldiary.service.diary.LoginService;
import com.zaynukov.dev.angeldiary.service.note.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
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
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final NoteService noteService;
    private final LoginService loginService;

    @Inject
    public LoginController(NoteService noteService, LoginService loginService) {
        this.noteService = noteService;
        this.loginService = loginService;
    }

    @GetMapping("")
    public String index() {
        return "redirect:/main";
    }

    @GetMapping("/login")
    public String signInPage(Authentication user) {
        if (user != null) {
            return "redirect:/main";
        } else
            return "login-in";
    }

    @GetMapping("/main")
    public String noteList() {
        return "main";
    }

    @PostMapping("/main")
    public String noteList2() {
        return "redirect:/main";
    }

    @GetMapping("/create-diary")
    public String createDiary(Authentication user) {
        if (user != null)
            return "redirect:/main";
        return "diary-create";
    }

    @PostMapping("/create-diary")
    public ModelAndView createDiary(Authentication user,
                                    @RequestParam String login,
                                    @RequestParam String password) {
            if (user != null)
                return new ModelAndView("redirect:/main");


        ModelAndView mv = new ModelAndView("login-in");
        try {
            loginService.createDiary(login, password);
        } catch (DiaryIsExistException e) {
            logger.error("Дневник с таким логином существует.", e);
            mv.addObject("e", e);
        } catch (SQLException e) {
            logger.error("Ошибка при работе с сервисом базы данных - не создан новый дневник", e);
            mv.addObject("e", e);
        }
        return mv;
    }

    @GetMapping("/create-note")
    public ModelAndView createNotePage() {
        return new ModelAndView("note-create");
    }

    @GetMapping("/edit-note")
    public ModelAndView editNotePage() {
        return new ModelAndView("note-edit");
    }


}

