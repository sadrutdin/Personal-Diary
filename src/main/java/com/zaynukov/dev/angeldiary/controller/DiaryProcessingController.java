package com.zaynukov.dev.angeldiary.controller;

import com.zaynukov.dev.angeldiary.exception.DiaryIsExistException;
import com.zaynukov.dev.angeldiary.exception.DiaryParamIsBadFormat;
import com.zaynukov.dev.angeldiary.service.diary.LoginService;
import com.zaynukov.dev.angeldiary.service.note.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.sql.SQLException;

@Controller
public class DiaryProcessingController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final LoginService loginService;
    private final NoteService noteService;

    @Inject
    public DiaryProcessingController(LoginService loginService, NoteService noteService) {
        this.loginService = loginService;
        this.noteService = noteService;
    }

    @PostMapping("/create-note")
    public ModelAndView createNote(Authentication user,
                                   @RequestParam String title,
                                   @RequestParam String text) {

        if (user == null) // Если пользователь не вошёл в дневник
            return new ModelAndView("redirect:/"); // Отправить на страницу входа

        try {
            noteService.saveNote(user, title, text);
        } catch (SQLException e) {
            logger.error("Ошибка при работе с БД", e);
        } catch (DiaryParamIsBadFormat e) {
            logger.error("Некорректные параметры", e);
        }

        return new ModelAndView("redirect:/main");
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


    @PostMapping("/main")
    public String noteList2() {
        return "redirect:/main";
    }

    @PostMapping("/edit-note")
    public ModelAndView editNotePage(Authentication user,
                                     @RequestParam int id,
                                     @RequestParam String title,
                                     @RequestParam String text) {

        if (user == null) return new ModelAndView("redirect:/");

        try {
            noteService.editNote(user, id, title, text);
            return new ModelAndView("redirect:/main");
        } catch (SQLException e) {
            logger.error("Ошибка при работе с БД", e);
            return new ModelAndView("redirect:/");
        }
    }


    @PostMapping("/delete-note")
    public String deleteNote(Authentication user,
                             RedirectAttributes redirectAttributes,
                             @RequestParam int id) {
        try {
            noteService.deleteNote(user, id);
            redirectAttributes.addFlashAttribute("msg", "Запись успешно удалена");
        } catch (SQLException e) {
            String errorMsg = "Ошибка при работе с базой данных - запись дневника не удалён";
            logger.error(errorMsg, e);
            redirectAttributes.addFlashAttribute("errorMsg", errorMsg);
        }
        return "redirect:/main";
    }


}
