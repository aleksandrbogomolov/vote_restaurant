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
    private UserService service;

    public User create(User user) {
        user.setId(null);
        log.info("create user {}", user);
        service.save(user);
        return user;
    }

    public void update(User user, int id) {
        user.setId(id);
        log.info("update user {}", user);
        service.update(user);
    }

    public void delete(int id) {
        log.info("delete user with id {}", id);
        service.delete(id);
    }

    public User get(int id) {
        log.info("get user with id {}", id);
        return service.get(id);
    }

    public User getByEmail(String email) {
        log.info("get user with email {}", email);
        return service.getByEmail(email);
    }

    public List<User> getAll() {
        log.info("get all users");
        return service.getAll();
    }
}
