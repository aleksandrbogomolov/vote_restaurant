package com.aleksandrbogomolov.vote_restaurant.model.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.NamedEntity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id AND d.menu.id=:menu_id"),
        @NamedQuery(name = Dish.GET_ALL, query = "SELECT d FROM Dish d WHERE d.menu.id=:menu_id ORDER BY d.name"),
        @NamedQuery(name = Dish.GET, query = "SELECT d FROM Dish d WHERE d.id=:id AND d.menu.id=:menu_id"),
        @NamedQuery(name = Dish.UPDATE, query = "UPDATE Dish d SET d.name=:name, d.price=:price WHERE d.id=:id AND d.menu.id=:menu_id")
})
@Entity
@Table(name = "dishes")
public class Dish extends NamedEntity {

    public static final String DELETE = "Dish.delete";
    public static final String UPDATE = "Dish.update";
    public static final String GET_ALL = "Dish.getAll";
    public static final String GET = "Dish.get";

    @Column(name = "price")
    protected int price;

    @JoinColumn(name = "menu_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    public Dish() {
    }

    public Dish(Dish d) {
        this(d.getId(), d.getName(), d.getPrice());
    }

    public Dish(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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
