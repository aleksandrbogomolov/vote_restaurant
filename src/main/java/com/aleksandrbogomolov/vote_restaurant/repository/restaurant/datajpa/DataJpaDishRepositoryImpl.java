package com.aleksandrbogomolov.vote_restaurant.repository.restaurant.datajpa;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.MenuDishRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaDishRepositoryImpl implements MenuDishRepository<Dish> {

    @Override
    public Dish save(Dish entity, int restaurant_id) {
        return null;
    }

    @Override
    public boolean delete(int id, int restaurant_id) {
        return false;
    }

    @Override
    public Dish get(int id, int restaurant_id) {
        return null;
    }

    @Override
    public List<Dish> getAll(int restaurant_id) {
        return null;
    }
}
