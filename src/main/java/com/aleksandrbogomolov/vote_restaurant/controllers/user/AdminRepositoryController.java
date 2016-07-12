package com.aleksandrbogomolov.vote_restaurant.controllers.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "admin")
public class AdminRepositoryController {

    private static Logger logger = LoggerFactory.getLogger(AdminRepositoryController.class);

    @RequestMapping(value = "page")
    public String getAdminPage() {
        logger.info("get admin page");
        return "admin";
    }
}
