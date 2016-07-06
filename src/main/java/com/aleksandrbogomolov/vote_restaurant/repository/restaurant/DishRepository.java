package com.aleksandrbogomolov.vote_restaurant.repository.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;

import java.util.List;

public interface DishRepository {

    Dish save(Dish entity, int restaurant_id);

    boolean delete(int id, int restaurant_id);

    Dish get(int id, int restaurant_id);

    List<Dish> getAll(int restaurant_id);
}
