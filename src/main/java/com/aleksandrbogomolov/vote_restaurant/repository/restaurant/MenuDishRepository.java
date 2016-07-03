package com.aleksandrbogomolov.vote_restaurant.repository.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;

import java.util.List;

public interface MenuDishRepository<T extends BaseEntity> {

    T save(T entity, int parent_id);

    boolean delete(int id, int parent_id);

    T get(int id, int parent_id);

    List<T> getAll(int parent_id);
}
