package com.aleksandrbogomolov.vote_restaurant.model.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;

import java.time.LocalDate;
import java.util.Set;

public class Menu extends BaseEntity {

    private LocalDate registered;

    private Set<Dish> dishes;

    public Menu() {
    }

    public Menu(Integer id, LocalDate registered) {
        super(id);
        this.registered = registered;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDate registered) {
        this.registered = registered;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "registered=" + registered +
                ", dishes=" + dishes +
                '}';
    }
}
