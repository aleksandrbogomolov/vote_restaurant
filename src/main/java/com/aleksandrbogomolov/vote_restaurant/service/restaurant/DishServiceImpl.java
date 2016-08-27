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

    private final DishRepository repository;

    @Autowired
    public DishServiceImpl(DishRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dish save(Dish entity, int restaurantId) {
        return repository.save(entity, restaurantId);
    }

    @Override
    public Dish update(Dish entity, int restaurantId) {
        return ExceptionUtil.checkNotFoundWithId(repository.save(entity, restaurantId), entity.getId());
    }

    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    @Override
    public Dish getOne(int id, int restaurantId) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.getOne(id, restaurantId), id);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }
}
