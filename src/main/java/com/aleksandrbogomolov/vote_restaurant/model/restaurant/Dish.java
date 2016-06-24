package com.aleksandrbogomolov.vote_restaurant.model.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.NamedIEntity;

public class Dish extends NamedIEntity {

    private int price;

    private int calories;

    private DishType dishType;

    public Dish() {
    }

    public Dish(Integer id, String name, int price, int calories, DishType dishType) {
        super(id, name);
        this.price = price;
        this.calories = calories;
        this.dishType = dishType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "price=" + price +
                ", calories=" + calories +
                ", dishType=" + dishType +
                '}';
    }
}
