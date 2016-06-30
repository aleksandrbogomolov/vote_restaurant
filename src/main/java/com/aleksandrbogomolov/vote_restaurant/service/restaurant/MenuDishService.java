package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;

import java.util.List;

public interface MenuDishService<T extends BaseEntity> {

    T save(T entity, int parent_id);

    T update(T entity, int parent_id);

    void delete(int id, int parent_id) throws NotFoundException;

    T get(int id, int parent_id) throws NotFoundException;

    List<T> getAll(int parent_id);
}
