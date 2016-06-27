package com.aleksandrbogomolov.vote_restaurant.repository.user;

import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.repository.BaseRepository;

public interface UserRepository extends BaseRepository<User> {

    User getByEmail(String email);
}
