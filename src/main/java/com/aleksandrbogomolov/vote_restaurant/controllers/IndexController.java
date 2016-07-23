package com.aleksandrbogomolov.vote_restaurant.controllers;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    private BaseService<Restaurant> restaurantService;

    @ModelAttribute("restaurants")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAll()
                .stream()
                .sorted((r1, r2) -> Integer.compare(r2.getVotes().size(), r1.getVotes().size()))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/")
    public String getLoginPage() {
        logger.info("login page");
        return "signin";
    }

    @RequestMapping(value = "admin", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAdminPage() {
        logger.info("get  all restaurants for admin page");
        return "admin";
    }

    @RequestMapping(value = "restaurant", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAll() {
        logger.info("get all restaurants for user page");
        return "user";
    }
}
