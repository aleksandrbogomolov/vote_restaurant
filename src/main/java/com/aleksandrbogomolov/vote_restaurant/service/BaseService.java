package com.aleksandrbogomolov.vote_restaurant.service;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    T save(T entity);

    default void update(T entity) {
        throw new UnsupportedOperationException();
    }

    void delete(int id) throws NotFoundException;

    default T getOne(int id) throws NotFoundException {
        throw new UnsupportedOperationException();
    }

    default List<T> getAll() {
        throw new UnsupportedOperationException();
    }
}
