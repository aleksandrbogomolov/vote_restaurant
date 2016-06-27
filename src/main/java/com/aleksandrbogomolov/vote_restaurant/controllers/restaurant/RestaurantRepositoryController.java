package com.aleksandrbogomolov.vote_restaurant.controllers.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RestaurantRepositoryController {

    private static Logger log = LoggerFactory.getLogger(RestaurantRepositoryController.class);

    @Autowired
    private BaseService<Restaurant> service;

    public Restaurant create(Restaurant restaurant) {
        restaurant.setId(null);
        log.info("create restaurant {}", restaurant);
        service.save(restaurant);
        return restaurant;
    }

    public void update(Restaurant restaurant, int id) {
        restaurant.setId(id);
        log.info("update restaurant {}", restaurant);
        service.update(restaurant);
    }

    public void delete(int id) {
        log.info("delete restaurant with id {}", id);
        service.delete(id);
    }

    public Restaurant get(int id) {
        log.info("get restaurant with id {}", id);
        return service.get(id);
    }

    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return service.getAll();
    }
}
