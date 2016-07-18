package com.aleksandrbogomolov.vote_restaurant.controllers.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping(value = "restaurant")
public class RestaurantRepositoryController {

    @Autowired
    private BaseService<Restaurant> service;

    @ModelAttribute("restaurants")
    public List<Restaurant> getAllRestaurants() {
        return service.getAll().stream().sorted((r1, r2) -> Integer.compare(r2.getVotes().size(), r1.getVotes().size())).collect(Collectors.toList());
    }

    @RequestMapping(value = "create")
    public String create(Restaurant restaurant) {
        logger.info("create restaurant {}", restaurant);
        restaurant.setId(null);
        service.save(restaurant);
        return "redirect:/admin/page";
    }

    @RequestMapping(value = "update")
    public String update(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name, @RequestParam(value = "address") String address) {
        logger.info("update restaurant with id {}", id);
        Restaurant restaurant = new Restaurant();
        restaurant.setId(id);
        restaurant.setName(name);
        restaurant.setAddress(address);
        service.update(restaurant);
        return "redirect:/admin/page";
    }

    @RequestMapping(value = "delete")
    public String delete(@RequestParam(value = "id") int id) {
        logger.info("delete restaurant with id {}", id);
        service.delete(id);
        return "redirect:/admin/page";
    }

    public Restaurant getOne(int id) {
        logger.info("get restaurant with id {}", id);
        return service.getOne(id);
    }

    @RequestMapping(value = "all")
    public String getAll() {
        logger.info("get all restaurants");
        return "restaurant";
    }
}
