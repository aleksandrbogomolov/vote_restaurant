package com.aleksandrbogomolov.vote_restaurant.model.user;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;
import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "votes")
public class Vote extends BaseEntity {

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
