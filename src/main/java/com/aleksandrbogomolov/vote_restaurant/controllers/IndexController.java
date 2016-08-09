package com.aleksandrbogomolov.vote_restaurant.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class IndexController implements ExceptionInfoHandler {

    @RequestMapping(value = "login")
    public String getLoginPage(ModelMap modelMap,
                               @RequestParam(value = "error", required = false) boolean error,
                               @RequestParam(value = "message", required = false) String message) {
        modelMap.put("error", error);
        modelMap.put("message", message);
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

    @RequestMapping(value = "access_denied")
    public String getAccessDenied() {
        return "access_denied";
    }
}
