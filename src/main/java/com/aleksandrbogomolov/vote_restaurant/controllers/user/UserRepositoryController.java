package com.aleksandrbogomolov.vote_restaurant.controllers.user;

import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserRepositoryController {

    private static Logger log = LoggerFactory.getLogger(UserRepositoryController.class);

    @Autowired
    private UserService userService;

    public User create(User user) {
        user.setId(null);
        log.info("create user {}", user);
        userService.save(user);
        return user;
    }

    public void update(User user, int id) {
        user.setId(id);
        log.info("update user {}", user);
        userService.save(user);
    }

    public void delete(int id) {
        log.info("delete user with id {}", id);
        userService.delete(id);
    }

    public User get(int id) {
        log.info("get user with id {}", id);
        return userService.get(id);
    }

    public User getByEmail(String email) {
        log.info("get user with email {}", email);
        return userService.getByEmail(email);
    }

    public List<User> getAll() {
        log.info("get all users");
        return userService.getAll();
    }
}
