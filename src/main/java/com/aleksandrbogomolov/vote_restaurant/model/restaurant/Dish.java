package com.aleksandrbogomolov.vote_restaurant.model.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.NamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishes")
public class Dish extends NamedEntity {

    @NotNull
    @Column(name = "price")
    protected int price;

    @NotNull
    @Column(name = "type_dish", nullable = false)
    private int typeDish;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Dish d) {
        this(d.getId(), d.getName(), d.getPrice(), d.getTypeDish());
    }

    public Dish(Integer id, String name, int price, int typeDish) {
        super(id, name);
        this.price = price;
        this.typeDish = typeDish;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTypeDish() {
        return typeDish;
    }

    public void setTypeDish(int typeDish) {
        this.typeDish = typeDish;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                "name=" + name +
                "price=" + price +
                '}';
    }
}
