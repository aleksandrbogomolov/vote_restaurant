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
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    protected Set<Vote> votes;
    @SuppressWarnings("JpaQlInspection")
    @OrderBy(value = "typeDish ASC")
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private List<Dish> dishes; //TODO mb change to Set

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
