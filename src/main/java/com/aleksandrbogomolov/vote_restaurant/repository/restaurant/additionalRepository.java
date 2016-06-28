package com.aleksandrbogomolov.vote_restaurant.repository.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;
import com.aleksandrbogomolov.vote_restaurant.repository.BaseRepository;

public interface AdditionalRepository<T extends BaseEntity> extends BaseRepository<T> {

    boolean clearAll();

    T save(T entity, Integer id);
}
