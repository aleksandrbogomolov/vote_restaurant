package com.aleksandrbogomolov.vote_restaurant.repository.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;

import java.util.List;

public interface DishRepository {

    Dish save(Dish entity, int restaurantId);

    @SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
    boolean delete(int id, int restaurantId);

    Dish getOne(int id, int restaurantId);

    List<Dish> getAll(int restaurantId);
}
