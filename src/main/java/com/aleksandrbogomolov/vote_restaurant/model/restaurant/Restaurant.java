package com.aleksandrbogomolov.vote_restaurant.model.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.NamedEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "restaurants")
public class Restaurant extends NamedEntity {

    @NotEmpty
    @Column(name = "address")
    protected String address;

    @SuppressWarnings("JpaQlInspection")
    @OrderBy(value = "typeDish ASC")
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private List<Dish> dishes;

    @Column(name = "votes")
    protected int votes;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getAddress(), r.getVotes());
    }

    public Restaurant(Integer id, String name, String address, Integer votes) {
        super(id, name);
        this.address = address;
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                "address='" + address + '\'' +
                "votes='" + votes + '\'' +
                '}';
    }
}
