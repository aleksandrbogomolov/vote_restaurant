package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;

import java.util.List;

public interface MenuDishService<T extends BaseEntity> {

    T save(T entity, Integer id);

    void update(T entity, Integer id);

    void delete(int id) throws NotFoundException;

    T get(int id) throws NotFoundException;

    List<T> getAll();
}
