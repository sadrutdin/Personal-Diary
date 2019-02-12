package com.zaynukov.dev.angeldiary.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {

//    private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @GetMapping("")
    public String index() {
        return "redirect:/main";
    }

    @GetMapping("/login")
    public String signInPage(Authentication user) {
        return user != null ? "redirect:/main" : "login-in";
    }

    @GetMapping("/create-diary")
    public String createDiary(Authentication user) {
        if (user != null)
            return "redirect:/main";

        return "diary-create";
    }
}

