package com.aleksandrbogomolov.vote_restaurant.repository;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;

import java.util.List;

public interface BaseRepository<T extends BaseEntity> {

    T save(T entity);

    boolean delete(int id);

    default T getOne(int id) {
        throw new UnsupportedOperationException();
    }

    default List<T> getAll() {
        throw new UnsupportedOperationException();
    }
}
