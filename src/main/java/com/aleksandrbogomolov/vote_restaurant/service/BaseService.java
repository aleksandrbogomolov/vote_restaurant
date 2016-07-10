package com.aleksandrbogomolov.vote_restaurant.service;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    T save(T entity);

    void update(T entity);

    void delete(int id) throws NotFoundException;

    T getOne(int id) throws NotFoundException;

    List<T> getAll();
}
