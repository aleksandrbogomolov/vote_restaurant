package com.aleksandrbogomolov.vote_restaurant.model.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.NamedEntity;

public class Dish extends NamedEntity {

    private int price;

    public Dish() {
    }

    public Dish(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "price=" + price +
                '}';
    }
}
