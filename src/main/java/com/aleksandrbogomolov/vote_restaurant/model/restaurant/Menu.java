package com.aleksandrbogomolov.vote_restaurant.model.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id"),
        @NamedQuery(name = Menu.GET_ALL, query = "SELECT m FROM Menu m ORDER BY m.registered DESC"),
        @NamedQuery(name = Menu.CLEAR_ALL, query = "DELETE FROM Menu m")
})
@Entity
@Table(name = "menus")
public class Menu extends BaseEntity {

    public static final String DELETE = "Menu.delete";
    public static final String GET_ALL = "Menu.getAll";
    public static final String CLEAR_ALL = "Menu.clearAll";

    @NotNull
    @Column(name = "registered")
    private Date registered;

    public Menu() {
    }

    public Menu(Integer id, Date registered) {
        super(id);
        this.registered = registered;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "registered=" + registered +
                '}';
    }
}
