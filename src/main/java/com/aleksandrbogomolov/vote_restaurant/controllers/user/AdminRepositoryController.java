package com.aleksandrbogomolov.vote_restaurant.controllers.user;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping(value = "admin")
public class AdminRepositoryController {

    @Autowired
    private BaseService<Restaurant> service;

    @ModelAttribute("restaurants")
    public List<Restaurant> getAllRestaurants() {
        return service.getAll().stream().sorted((r1, r2) -> Integer.compare(r2.getVotes().size(), r1.getVotes().size())).collect(Collectors.toList());
    }

    @RequestMapping(value = "page")
    public String getAdminPage() {
        logger.info("get admin page");
        return "admin";
    }
}
