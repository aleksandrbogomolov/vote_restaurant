package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.MenuDishRepository;
import com.aleksandrbogomolov.vote_restaurant.util.exception.ExceptionUtil;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements MenuDishService<Dish> {

    @Autowired
    private MenuDishRepository<Dish> repository;

    @Override
    public Dish save(Dish dish, int menu_id) {
        return repository.save(dish, menu_id);
    }

    @Override
    public Dish update(Dish dish, int menu_id) {
        return ExceptionUtil.checkNotFoundWithId(repository.save(dish, menu_id), dish.getId());
    }

    @Override
    public void delete(int id, int menu_id) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, menu_id), id);
    }

    @Override
    public Dish get(int id, int menu_id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id, menu_id), id);
    }

    @Override
    public List<Dish> getAll(int menu_id) {
        return repository.getAll(menu_id);
    }
}
