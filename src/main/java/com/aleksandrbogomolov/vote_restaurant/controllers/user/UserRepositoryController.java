package com.aleksandrbogomolov.vote_restaurant.controllers.user;

import com.aleksandrbogomolov.vote_restaurant.controllers.ExceptionInfoHandler;
import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;
import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.service.user.UserService;
import com.aleksandrbogomolov.vote_restaurant.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@Slf4j
@RequestMapping(value = "profile")
public class UserRepositoryController implements ExceptionInfoHandler {

    private final UserService<User> service;

    public static boolean isTest;

    @Autowired
    public UserRepositoryController(UserService<User> service) {
        this.service = service;
    }

    @SuppressWarnings("NonBooleanMethodNameMayNotStartWithQuestion")
    private void checkModificationAllowed(Integer id) {
        if (!isTest && id < BaseEntity.START_SEQ + 2) {
            throw new ValidationException("Admin/User are not available for modification. Register your own user please.");
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getOne() {
        logger.info("get user");
        return service.getByEmail(Util.getPrincipal());
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public void register(@Valid User user) {
        service.save(user);
        logger.info("create user {}", user);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(@Valid User user) {
        checkModificationAllowed(user.getId());
        if (isPasswordEquals(user)) {
            logger.info("update user {}", user);
            service.update(user);
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete(User user) {
        checkModificationAllowed(user.getId());
        if (isPasswordEquals(user)) {
            logger.info("delete user {}", user);
            service.delete(user.getId());
        }
    }

    private boolean isPasswordEquals(User user) {
        return Util.isPasswordMatches(user.getPassword(), service.getOne(user.getId()).getPassword());
    }
}
