package com.aleksandrbogomolov.vote_restaurant.controllers.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.model.to.DishTo;
import com.aleksandrbogomolov.vote_restaurant.service.restaurant.DishService;
import com.aleksandrbogomolov.vote_restaurant.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(value = "dish")
public class DishRepositoryController {

    private final DishService service;

    @Autowired
    public DishRepositoryController(DishService service) {
        this.service = service;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createOrUpdate(@Valid DishTo dishTo) {
        Dish dish = Util.createNewFromDishTo(dishTo);
        if (dish.isNew()) {
            logger.info("create dish {}", dish);
            service.save(dish, dishTo.getRestaurant());
        } else {
            logger.info("update dish {}", dish);
            service.update(dish, dishTo.getRestaurant());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{restaurantId}/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id,
                       @PathVariable("restaurantId") int restaurantId) {
        logger.info("delete dish with id {}", id);
        service.delete(id, restaurantId);
    }
}
