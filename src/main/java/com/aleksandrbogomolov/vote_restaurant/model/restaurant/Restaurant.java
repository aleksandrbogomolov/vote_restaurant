package com.aleksandrbogomolov.vote_restaurant.model.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.NamedEntity;
import com.aleksandrbogomolov.vote_restaurant.model.user.Vote;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "restaurants")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Restaurant extends NamedEntity {

    @NotEmpty
    @Column(name = "address")
    protected String address;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    protected Set<Vote> votes;

    @SuppressWarnings("JpaQlInspection")
    @OrderBy(value = "typeDish ASC")
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private Set<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getAddress());
    }

    public Restaurant(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                "address='" + address + '\'' +
                '}';
    }
}
