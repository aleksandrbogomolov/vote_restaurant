package com.aleksandrbogomolov.vote_restaurant.repository.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;

import java.util.List;

public interface MenuDishRepository<T extends BaseEntity> {

    T save(T entity, Integer id);

    boolean delete(int id);

    T get(int id);

    List<T> getAll();
}
