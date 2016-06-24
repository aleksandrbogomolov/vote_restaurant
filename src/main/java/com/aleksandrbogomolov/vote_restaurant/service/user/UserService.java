package com.aleksandrbogomolov.vote_restaurant.service.user;

import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User save(User user);

    void update(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();
}
