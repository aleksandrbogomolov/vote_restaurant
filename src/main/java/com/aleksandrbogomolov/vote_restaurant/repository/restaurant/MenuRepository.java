package com.aleksandrbogomolov.vote_restaurant.repository.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Menu;
import com.aleksandrbogomolov.vote_restaurant.repository.BaseRepository;

public interface MenuRepository extends BaseRepository<Menu> {

    boolean clearAll();
}
