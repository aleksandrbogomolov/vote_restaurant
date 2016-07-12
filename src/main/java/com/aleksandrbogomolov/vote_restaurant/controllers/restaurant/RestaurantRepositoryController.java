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
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "restaurant")
public class RestaurantRepositoryController {

    private static Logger logger = LoggerFactory.getLogger(RestaurantRepositoryController.class);

    @Autowired
    private BaseService<Restaurant> service;

    @ModelAttribute("restaurants")
    public List<Restaurant> getAllRestaurants() {
        return service.getAll().stream().sorted((r1, r2) -> Integer.compare(r2.getVotes().size(), r1.getVotes().size())).collect(Collectors.toList());
    }

    public Restaurant create(Restaurant restaurant) {
        logger.info("create restaurant {}", restaurant);
        restaurant.setId(null);
        service.save(restaurant);
        return restaurant;
    }

    public void update(Restaurant restaurant, int id) {
        logger.info("update restaurant with id {}", id);
        restaurant.setId(id);
        service.update(restaurant);
    }

    public void delete(int id) {
        logger.info("delete restaurant with id {}", id);
        service.delete(id);
    }

    public Restaurant getOne(int id) {
        logger.info("getOne restaurant with id {}", id);
        return service.getOne(id);
    }

    @RequestMapping(value = "all")
    public String getAll(Restaurant restaurant) {
        logger.info("get all restaurants");
        return "restaurant";
    }
}
