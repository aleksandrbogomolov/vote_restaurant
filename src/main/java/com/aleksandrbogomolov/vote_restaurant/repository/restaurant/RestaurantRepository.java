package com.aleksandrbogomolov.vote_restaurant.repository.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.repository.BaseRepository;

import java.util.List;

public interface RestaurantRepository extends BaseRepository<Restaurant> {

    List<Restaurant> getAllWithMenu();
}
