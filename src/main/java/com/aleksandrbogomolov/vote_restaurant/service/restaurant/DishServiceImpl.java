package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.DishRepository;
import com.aleksandrbogomolov.vote_restaurant.util.exception.ExceptionUtil;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository repository;

    @Override
    public Dish save(Dish dish, int restaurant_id) {
        return repository.save(dish, restaurant_id);
    }

    @Override
    public Dish update(Dish dish, int restaurant_id) {
        return ExceptionUtil.checkNotFoundWithId(repository.save(dish, restaurant_id), dish.getId());
    }

    @Override
    public void delete(int id, int restaurant_id) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, restaurant_id), id);
    }

    @Override
    public Dish get(int id, int restaurant_id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id, restaurant_id), id);
    }

    @Override
    public List<Dish> getAll(int restaurant_id) {
        return repository.getAll(restaurant_id);
    }
}
