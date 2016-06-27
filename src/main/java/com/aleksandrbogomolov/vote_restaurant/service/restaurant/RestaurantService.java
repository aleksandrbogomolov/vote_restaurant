package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService<T extends BaseEntity> {

    T save(T entity);

    void update(T entity);

    void delete(int id) throws NotFoundException;

    T get(int id) throws NotFoundException;

    List<T> getAll();
}
