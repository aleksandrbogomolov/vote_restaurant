package com.aleksandrbogomolov.vote_restaurant.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    @RequestMapping(value = "/")
    public String getLoginPage() {
        logger.info("get login page");
        return "signin";
    }

    @RequestMapping(value = "admin")
    public String getAdminPage() {
        logger.info("get admin page");
        return "admin";
    }

    @RequestMapping(value = "restaurant")
    public String getUserPage() {
        logger.info("get user page");
        return "user";
    }
}
