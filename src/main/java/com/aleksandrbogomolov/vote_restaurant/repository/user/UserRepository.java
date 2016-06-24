package com.aleksandrbogomolov.vote_restaurant.repository.user;

import com.aleksandrbogomolov.vote_restaurant.model.user.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();
}
