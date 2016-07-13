package com.aleksandrbogomolov.vote_restaurant.controllers.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping(value = "admin")
public class AdminRepositoryController {

    @RequestMapping(value = "page")
    public String getAdminPage() {
        logger.info("get admin page");
        return "admin";
    }
}
