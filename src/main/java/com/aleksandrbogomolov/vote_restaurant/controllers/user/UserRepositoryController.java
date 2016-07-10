package com.aleksandrbogomolov.vote_restaurant.controllers.user;

import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserRepositoryController {

    private static Logger logger = LoggerFactory.getLogger(UserRepositoryController.class);

    @Autowired
    private UserService<User> service;

    @ModelAttribute("allUsers")
    public List<User> getAllUsers() {
        return service.getAll();
    }

    public User create(User user) {
        user.setId(null);
        logger.info("create user {}", user);
        service.save(user);
        return user;
    }

    public void update(User user, int id) {
        user.setId(id);
        logger.info("update user {}", user);
        service.update(user);
    }

    public void delete(int id) {
        logger.info("delete user with id {}", id);
        service.delete(id);
    }

    public User getOne(int id) {
        logger.info("getOne user with id {}", id);
        return service.getOne(id);
    }

    public User getByEmail(String email) {
        logger.info("getOne user with email {}", email);
        return service.getByEmail(email);
    }

    @RequestMapping(value = "/allUsers")
    public String getAll(User user) {
        logger.info("getOne all users");
        return "user_page";
    }
}
