package com.aleksandrbogomolov.vote_restaurant.controllers.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RestaurantRepositoryController {

    private static Logger log = LoggerFactory.getLogger(RestaurantRepositoryController.class);

    @Autowired
    private BaseService<Restaurant> service;

    @ModelAttribute("allRestaurant")
    public List<Restaurant> getAllRestaurants() {
        return service.getAll();
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        restaurant.setId(null);
        service.save(restaurant);
        return restaurant;
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update restaurant with id {}", id);
        restaurant.setId(id);
        service.update(restaurant);
    }

    public void delete(int id) {
        log.info("delete restaurant with id {}", id);
        service.delete(id);
    }

    public Restaurant get(int id) {
        log.info("getOne restaurant with id {}", id);
        return service.getOne(id);
    }

    @RequestMapping(value = "/")
    public String getAll(Restaurant restaurant) {
        log.info("getOne all restaurants");
        return "user_page";
    }
}
