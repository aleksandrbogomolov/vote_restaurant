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
    protected User user;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    protected Restaurant restaurant;

    public Vote() {
    }

    public Vote(Vote v) {
        this(v.getId());
    }

    public Vote(Integer id) {
        super(id);
    }

    public Vote(Integer id, User user, Restaurant restaurant) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
    }
}
