package com.aleksandrbogomolov.vote_restaurant.controllers;

import com.aleksandrbogomolov.vote_restaurant.model.user.Role;
import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@Slf4j
public class IndexController implements ExceptionInfoHandler {

    private final UserService<User> service;

    @Autowired
    public IndexController(UserService<User> service) {
        this.service = service;
    }

    @RequestMapping(value = "login")
    public String getLoginPage(ModelMap modelMap,
                               @RequestParam(value = "error", required = false) boolean error,
                               @RequestParam(value = "message", required = false) String message) {
        modelMap.put("error", error);
        modelMap.put("message", message);
        logger.info("get login page");
        return "signin";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@Valid User user, SessionStatus status) {
        user.setId(null);
        user.setRole(Role.ROLE_USER);
        service.save(user);
        status.setComplete();
        logger.info("create user {}", user);
        return "redirect:login?message=signin.form.register.success";
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
