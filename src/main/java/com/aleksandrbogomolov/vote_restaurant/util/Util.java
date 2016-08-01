package com.aleksandrbogomolov.vote_restaurant.util;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.model.to.DishTo;

public class Util {

    public static Dish createNewFromDishTo(DishTo dishTo) {
        return new Dish(dishTo.getId(), dishTo.getName(), dishTo.getPrice(), dishTo.getTypeDish());
    }
}
