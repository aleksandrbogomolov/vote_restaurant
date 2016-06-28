package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.AdditionalRepository;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements AdditionalService<Dish> {

    @Autowired
    private AdditionalRepository<Dish> repository;

    @Override
    public Dish save(Dish dish, Integer id) {
        return repository.save(dish, id);
    }

    @Override
    public void update(Dish dish, Integer id) {
        repository.save(dish, id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    public Dish get(int id) throws NotFoundException {
        return repository.get(id);
    }

    @Override
    public List<Dish> getAll() {
        return repository.getAll();
    }

//    Unimplemented methods

    @Override
    public Dish save(Dish dish) {
        return null;
    }

    @Override
    public void update(Dish dish) {

    }

    @Override
    public void clearAll() {

    }
}
