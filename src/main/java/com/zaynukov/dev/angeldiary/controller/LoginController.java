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
        return "redirect:/sign-in";
    }

    @GetMapping("/sign-in")
    public String signInPage(Authentication user) {
        if (user != null) {
            return "redirect:/note-list";
        } else
            return "sign-in";
    }

//    @PostMapping("/sign-in")
//    public ModelAndView signInProcessing(@RequestParam String login,
//                                         @RequestParam String password) {
//        logger.info("1111111111");
//        boolean match;
//        try {
//            match = loginService.match(login, password);
//        } catch (DiaryIsNotExistsException e) {
//            logger.error("Дневника с таким логином '" + login + "' не существует.", e);
//            return new ModelAndView("redirect:/sign-in")
//                    .addObject("e", e);
//        }
//
//        if (!match) return new ModelAndView("redirect:/sign-in")
//                .addObject("status", "invalid-password");
//
//
//        try {
//
//            return new ModelAndView("/note-list")
//                    .addObject(noteService.allNotes(login, password));
//
//        } catch (SQLException e) {
//            logger.error("Ошибка при работе с сервисом базы данных - метод получения списка всех записей дневника", e);
//            return new ModelAndView("redirect:/sign-in")
//                    .addObject("e", e);
//        }
//    }


    @GetMapping("/note-list")
    public String noteList() {
        return "note-list";
    }

    @PostMapping("/note-list")
    public String noteList2() {
        return "redirect:/note-list";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public ModelAndView createDiary(@RequestParam String login,
                                    @RequestParam String password) {

        logger.info("2222222222");


        ModelAndView mv = new ModelAndView("redirect:/sign-in");
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


}

