package com.zaynukov.dev.angeldiary.controller;

import com.zaynukov.dev.angeldiary.service.note.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Collections;

@Controller
public class PageController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final NoteService noteService;

    @Inject
    public PageController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/main")
    public ModelAndView noteList(
            Authentication user,
            @RequestParam(required = false, value = "daterange") String dateRange,
            @RequestParam(required = false, value = "search") String search) {

        ModelAndView mv = new ModelAndView();

        if (user == null) {
            mv.setViewName("redirect:/");
            return mv;
        }

        mv.setViewName("main");

        String items = "items";

        try {
            mv.addObject(items, noteService.notesWithFilter(user, dateRange, search));
        } catch (SQLException e) {
            logger.error("Ошибка при работе с БД", e);
            mv.addObject(items, Collections.emptyList());
            mv.addObject(e);
        }

        return mv;
    }

    @GetMapping("/create-note")
    public ModelAndView createNotePage(Authentication user) {

        if (user == null) return new ModelAndView("redirect:/");

        return new ModelAndView("note-create");
    }



    @GetMapping("/view")
    public ModelAndView editNotePage2(Authentication user,
                                      @RequestParam int id) {

        if (user == null) return new ModelAndView("redirect:/");

        try {
            return new ModelAndView("note-view-edit")
                    .addObject("dto", noteService.getNote(user, id));
        } catch (SQLException e) {
            logger.error("Ошибка при работе с БД", e);
            return new ModelAndView("redirect:/");
        }
    }
}
