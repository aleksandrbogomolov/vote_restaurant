package com.aleksandrbogomolov.vote_restaurant.model.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.NamedEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT r FROM Restaurant r ORDER BY r.name")
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity {

    public static final String DELETE = "Restaurant.delete";
    public static final String GET_ALL = "Restaurant.getAll";

    @NotEmpty
    @Column(name = "address")
    protected String address;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getAddress());
    }

    public Restaurant(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                "address='" + address + '\'' +
                '}';
    }
}
