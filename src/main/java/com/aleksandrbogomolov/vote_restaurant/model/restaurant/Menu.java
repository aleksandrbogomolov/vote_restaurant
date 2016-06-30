package com.aleksandrbogomolov.vote_restaurant.model.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurant_id"),
        @NamedQuery(name = Menu.GET_ALL, query = "SELECT m FROM Menu m WHERE m.restaurant.id=:restaurant_id ORDER BY m.registered DESC"),
        @NamedQuery(name = Menu.UPDATE, query = "UPDATE Menu m SET m.registered=:registered WHERE m.id=:id AND m.restaurant.id=:restaurant_id"),
        @NamedQuery(name = Menu.GET, query = "SELECT m FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurant_id")
})
@Entity
@Table(name = "menus")
public class Menu extends BaseEntity {

    public static final String DELETE = "Menu.delete";
    public static final String UPDATE = "Menu.update";
    public static final String GET_ALL = "Menu.getAll";
    public static final String GET = "Menu.get";

    @NotNull
    @Column(name = "registered")
    protected LocalDateTime registered;

    @NotNull
    @JoinColumn(name = "restaurant_id")
    @ManyToOne(fetch = FetchType.LAZY)
    protected Restaurant restaurant;

    public Menu() {
    }

    public Menu(Menu m) {
        this(m.getId(), m.getRegistered());
    }

    public Menu(Integer id, LocalDateTime registered) {
        super(id);
        this.registered = registered;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                "registered=" + registered +
                '}';
    }
}
