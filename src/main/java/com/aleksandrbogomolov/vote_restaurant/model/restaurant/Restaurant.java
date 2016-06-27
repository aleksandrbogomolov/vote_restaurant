package com.aleksandrbogomolov.vote_restaurant.model.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.NamedEntity;

import java.util.Set;

public class Restaurant extends NamedEntity {

    private String address;

    private Set<Menu> menus;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String address, Set<Menu> menus) {
        super(id, name);
        this.address = address;
        this.menus = menus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
                ", menus=" + menus +
                '}';
    }
}
