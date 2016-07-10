package com.aleksandrbogomolov.vote_restaurant.model.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.NamedEntity;
import com.aleksandrbogomolov.vote_restaurant.model.user.Vote;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    private List<Dish> dishes; //TODO mb change to Set

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    protected Set<Vote> votes;

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
                "name='" + name + '\'' +
                "address='" + address + '\'' +
                "votes='" + votes + '\'' +
                '}';
    }
}
