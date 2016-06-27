package com.aleksandrbogomolov.vote_restaurant.repository.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;

public interface AdditionalRestaurantRepository<T extends BaseEntity> extends MainRestaurantRepository<T> {

    boolean clearAll();
}
