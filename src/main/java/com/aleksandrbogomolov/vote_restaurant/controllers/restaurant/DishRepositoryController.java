package com.aleksandrbogomolov.vote_restaurant.controllers.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.service.restaurant.AdditionalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DishRepositoryController {

    private static Logger log = LoggerFactory.getLogger(DishRepositoryController.class);

    @Autowired
    private AdditionalService<Dish> service;

    public Dish create(Dish dish, Integer menu_id) {
        log.info("create dish {}", dish);
        dish.setId(null);
        service.save(dish, menu_id);
        return dish;
    }

    public void update(Dish dish, int id, Integer menu_id) {
        log.info("update dish with id {}", id);
        dish.setId(id);
        service.update(dish, menu_id);
    }

    public void delete(int id) {
        log.info("delete dish with id {}", id);
        service.delete(id);
    }

    public Dish get(int id) {
        log.info("get dish with id {}", id);
        return service.get(id);
    }

    public List<Dish> getAll() {
        log.info("get all dishes");
        return service.getAll();
    }
}
