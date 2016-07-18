package com.aleksandrbogomolov.vote_restaurant.controllers.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.service.restaurant.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "dish")
public class DishRepositoryController {

    @Autowired
    private DishService service;

    @RequestMapping(value = "create")
    public String create(@RequestParam("restaurantId") int restaurantId) {
        logger.info("create dish from restaurant with id {}", restaurantId);
        Dish dish = new Dish();
        dish.setId(null);
        dish.setName(" ");
        dish.setPrice(0);
        dish.setTypeDish(0);
        service.save(dish, restaurantId);
        return "redirect:/admin/page";
    }

    @RequestMapping(value = "update")
    public String update(@RequestParam(value = "id") int id, @RequestParam(value = "restaurantId") int restaurantId, @RequestParam(value = "name") String name, @RequestParam(value = "price") int price, @RequestParam(value = "typeDish") int type) {
        logger.info("update dish with id {}", id);
        Dish dish = service.getOne(id, restaurantId);
        dish.setName(name.trim());
        dish.setPrice(price);
        dish.setTypeDish(type);
        service.update(dish, restaurantId);
        return "redirect:/admin/page";
    }

    @RequestMapping(value = "delete")
    public String delete(@RequestParam(value = "id") int id, @RequestParam(value = "restaurantId") int restaurantId) {
        logger.info("delete dish with id {}", id);
        service.delete(id, restaurantId);
        return "redirect:/admin/page";
    }

    public Dish getOne(int id, int restaurantId) {
        logger.info("getOne dish with id {}", id);
        return service.getOne(id, restaurantId);
    }

    public List<Dish> getAll(int restaurantId) {
        logger.info("getOne all dishes");
        return service.getAll(restaurantId);
    }
}
