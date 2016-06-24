package com.aleksandrbogomolov.vote_restaurant.model.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.NamedEntity;

import java.time.LocalTime;
import java.util.Set;

public class Restaurant extends NamedEntity {

    private String address;

    private LocalTime timeToUp;

    private LocalTime timeToClose;

    private Set<Menu> menus;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String address, LocalTime timeToUp, LocalTime timeToClose, Set<Menu> menus) {
        super(id, name);
        this.address = address;
        this.timeToUp = timeToUp;
        this.timeToClose = timeToClose;
        this.menus = menus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalTime getTimeToUp() {
        return timeToUp;
    }

    public void setTimeToUp(LocalTime timeToUp) {
        this.timeToUp = timeToUp;
    }

    public LocalTime getTimeToClose() {
        return timeToClose;
    }

    public void setTimeToClose(LocalTime timeToClose) {
        this.timeToClose = timeToClose;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "address='" + address + '\'' +
                ", timeToUp=" + timeToUp +
                ", timeToClose=" + timeToClose +
                ", menus=" + menus +
                '}';
    }
}
