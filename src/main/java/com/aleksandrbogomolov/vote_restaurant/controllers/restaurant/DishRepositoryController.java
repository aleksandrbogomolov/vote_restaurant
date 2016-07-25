package com.aleksandrbogomolov.vote_restaurant.controllers.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.service.restaurant.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "dish")
public class DishRepositoryController {

    @Autowired
    private DishService service;

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestParam(value = "restaurant") int restaurantId,
                       @RequestParam(value = "id") int id,
                       @RequestParam(value = "name") String name,
                       @RequestParam(value = "price") int price,
                       @RequestParam(value = "typeDish") int type) {
        Dish dish = new Dish(id, name, price, type);
        if (id == 0) {
            logger.info("create dish from restaurant with id {}", restaurantId);
            dish.setId(null);
            service.save(dish, restaurantId);
        } else {
            logger.info("update dish with id {}", id);
            service.update(dish, restaurantId);
        }
    }

    @RequestMapping(value = "/{restaurantId}/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id,
                       @PathVariable("restaurantId") int restaurantId) {
        logger.info("delete dish with id {}", id);
        service.delete(id, restaurantId);
    }
}
