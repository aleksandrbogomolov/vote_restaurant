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
    public void create(@RequestParam(value = "restaurantId") int restaurantId,
                         @RequestParam(value = "name") String name,
                         @RequestParam(value = "price") int price,
                         @RequestParam(value = "type") int type) {
        logger.info("create dish from restaurant with id {}", restaurantId);
        Dish dish = new Dish();
        dish.setId(null);
        dish.setName(name);
        dish.setPrice(price);
        dish.setTypeDish(type);
        service.save(dish, restaurantId);
    }

    @RequestMapping(value = "update")
    public String update(@RequestParam(value = "id") int id,
                         @RequestParam(value = "restaurantId") int restaurantId,
                         @RequestParam(value = "name") String name,
                         @RequestParam(value = "price") int price,
                         @RequestParam(value = "typeDish") int type) {
        logger.info("update dish with id {}", id);
        Dish dish = service.getOne(id, restaurantId);
        dish.setName(name.trim());
        dish.setPrice(price);
        dish.setTypeDish(type);
        service.update(dish, restaurantId);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/{restaurantId}/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id,
                       @PathVariable("restaurantId") int restaurantId) {
        logger.info("delete dish with id {}", id);
        service.delete(id, restaurantId);
    }
}
