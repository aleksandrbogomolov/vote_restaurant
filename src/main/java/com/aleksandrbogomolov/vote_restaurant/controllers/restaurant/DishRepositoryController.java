package com.aleksandrbogomolov.vote_restaurant.controllers.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.service.restaurant.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "dish")
public class DishRepositoryController {

    @Autowired
    private DishService service;

    public Dish create(Dish dish, int restaurantId) {
        logger.info("create dish {}", dish);
        dish.setId(null);
        service.save(dish, restaurantId);
        return dish;
    }

    public void update(Dish dish, int id, int restaurantId) {
        logger.info("update dish with id {}", id);
        dish.setId(id);
        service.update(dish, restaurantId);
    }

    @RequestMapping(value = "delete")
    public String delete(@RequestParam(value = "id") int id, @RequestParam(value = "restaurantId") int restaurantId) {
        logger.info("delete dish with id {}", id);
        service.delete(id, restaurantId);
        return "redirect:/admin/page";
    }

    public Dish getOne(int id, int restaurantId) {
        logger.info("getOne dish with id {}", id);
        return service.getOne(id, restaurantId);
    }

    public List<Dish> getAll(int restaurantId) {
        logger.info("getOne all dishes");
        return service.getAll(restaurantId);
    }
}
