package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.service.BaseService;

import java.util.List;

public interface RestaurantService extends BaseService<Restaurant> {

    List<Restaurant> getAllWithMenu();
}
