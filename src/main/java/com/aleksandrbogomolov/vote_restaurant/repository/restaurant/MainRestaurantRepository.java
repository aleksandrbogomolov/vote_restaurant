package com.aleksandrbogomolov.vote_restaurant.repository.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;

import java.util.List;

public interface MainRestaurantRepository<T extends BaseEntity> {

    T save(T entity);

    boolean delete(int id);

    T get(int id);

    List<T> getAll();
}
