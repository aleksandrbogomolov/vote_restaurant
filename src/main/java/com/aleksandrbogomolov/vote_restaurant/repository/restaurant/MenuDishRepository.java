package com.aleksandrbogomolov.vote_restaurant.repository.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;

import java.util.List;

public interface MenuDishRepository<T extends BaseEntity> {

    T save(T entity, int restaurant_id);

    boolean delete(int id, int restaurant_id);

    T get(int id, int restaurant_id);

    List<T> getAll(int restaurant_id);
}
