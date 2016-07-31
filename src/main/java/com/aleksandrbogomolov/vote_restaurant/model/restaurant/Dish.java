package com.aleksandrbogomolov.vote_restaurant.model.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.NamedEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "dishes")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Dish extends NamedEntity {

    @NotNull
    @Column(name = "price")
    protected int price;

    @NotNull
    @Column(name = "type_dish", nullable = false)
    protected int typeDish;

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

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                "name=" + name +
                "price=" + price +
                "typeDish=" + typeDish +
                '}';
    }
}
