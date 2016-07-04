package com.aleksandrbogomolov.vote_restaurant.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login")
    public String getLoginPage() {
        log.info("get login page");
        return "login";
    }
}
