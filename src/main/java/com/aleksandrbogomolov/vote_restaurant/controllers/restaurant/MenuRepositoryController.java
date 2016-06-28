package com.aleksandrbogomolov.vote_restaurant.controllers.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Menu;
import com.aleksandrbogomolov.vote_restaurant.service.restaurant.AdditionalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MenuRepositoryController {

    private static Logger log = LoggerFactory.getLogger(MenuRepositoryController.class);

    @Autowired
    private AdditionalService<Menu> service;

    public Menu create(Menu menu, int restaurant_id) {
        log.info("create menu {}", menu);
        menu.setId(null);
        service.save(menu, restaurant_id);
        return menu;
    }

    public void update(Menu menu, int id, int restaurant_id) {
        log.info("update menu with id {}", id);
        menu.setId(id);
        service.update(menu, restaurant_id);
    }

    public void delete(int id) {
        log.info("delete menu with id {}", id);
        service.delete(id);
    }

    public Menu get(int id) {
        log.info("get menu with id {}", id);
        return service.get(id);
    }

    public List<Menu> getAll() {
        log.info("get all menu");
        return service.getAll();
    }

    public void clearAll() {
        log.info("clear table menus");
        service.clearAll();
    }
}
