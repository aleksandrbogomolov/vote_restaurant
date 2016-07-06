package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;

import java.util.List;

public interface DishService {

    Dish save(Dish entity, int parent_id);

    Dish update(Dish entity, int parent_id);

    void delete(int id, int parent_id) throws NotFoundException;

    Dish get(int id, int parent_id) throws NotFoundException;

    List<Dish> getAll(int parent_id);
}
