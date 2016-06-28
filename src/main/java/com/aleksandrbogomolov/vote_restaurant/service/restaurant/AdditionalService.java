package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;
import com.aleksandrbogomolov.vote_restaurant.service.BaseService;

public interface AdditionalService<T extends BaseEntity> extends BaseService<T> {

    void clearAll();

    T save(T entity, Integer id);

    void update(T entity, Integer id);
}
