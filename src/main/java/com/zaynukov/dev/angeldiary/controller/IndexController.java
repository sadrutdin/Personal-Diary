package com.zaynukov.dev.angeldiary.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

}

