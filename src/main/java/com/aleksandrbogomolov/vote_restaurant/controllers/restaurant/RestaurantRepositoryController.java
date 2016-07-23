package com.aleksandrbogomolov.vote_restaurant.controllers.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "restaurant")
public class RestaurantRepositoryController {

    @Autowired
    private BaseService<Restaurant> service;

    @RequestMapping(method = RequestMethod.POST)
    public void create(Restaurant restaurant) {
        if (restaurant.getId() == null) {
            logger.info("create restaurant {}", restaurant);
            service.save(restaurant);
        } else {
            logger.info("update restaurant with id {}", restaurant.getId());
            service.update(restaurant);
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@RequestParam(value = "id") int id,
                         @RequestParam(value = "name") String name,
                         @RequestParam(value = "address") String address) {
        logger.info("update restaurant with id {}", id);
        Restaurant restaurant = new Restaurant();
        restaurant.setId(id);
        restaurant.setName(name);
        restaurant.setAddress(address);
        service.update(restaurant);
        return "redirect:/admin/page";
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam(value = "id") int id) {
        logger.info("delete restaurant with id {}", id);
        service.delete(id);
    }
}
