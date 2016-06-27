package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Menu;
import com.aleksandrbogomolov.vote_restaurant.service.BaseService;

public interface MenuService extends BaseService<Menu> {

    void clearAll();
}
