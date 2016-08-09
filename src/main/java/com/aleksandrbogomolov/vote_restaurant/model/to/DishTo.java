package com.aleksandrbogomolov.vote_restaurant.model.to;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DishTo {

    private Integer id;

    @NotEmpty
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer typeDish;

    @NotNull
    private int restaurant;

    public DishTo() {
    }

    public DishTo(int id, String name, int price, int typeDish, int restaurant) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.typeDish = typeDish;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", typeDish=" + typeDish +
                ", restaurant=" + restaurant +
                '}';
    }
}
