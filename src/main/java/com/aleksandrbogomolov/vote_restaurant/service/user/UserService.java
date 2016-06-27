package com.aleksandrbogomolov.vote_restaurant.service.user;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;
import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.service.BaseService;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;

public interface UserService<T extends BaseEntity> extends BaseService<T> {

    User getByEmail(String email) throws NotFoundException;
}
