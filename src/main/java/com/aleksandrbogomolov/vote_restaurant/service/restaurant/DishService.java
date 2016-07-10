package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;

import java.util.List;

public interface DishService {

    Dish save(Dish entity, int restaurantId);

    Dish update(Dish entity, int restaurantId);

    void delete(int id, int restaurantId) throws NotFoundException;

    Dish getOne(int id, int restaurantId) throws NotFoundException;

    List<Dish> getAll(int restaurantId);
}
