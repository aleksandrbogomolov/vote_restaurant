package com.aleksandrbogomolov.vote_restaurant.model.user;

import com.aleksandrbogomolov.vote_restaurant.model.NamedEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends NamedEntity {

    @NotEmpty
    @Email
    @Column(name = "email", unique = true)
    protected String email;

    @NotEmpty
    @Length(min = 5)
    @Column(name = "password")
    protected String password;

    @NotNull
    @Column(name = "enabled")
    protected boolean enabled = true;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    protected Role role;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vote")
    protected Vote vote;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private Date registered = new Date();

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRole(), u.getVote());
    }

    public User(Integer id, String name, String email, String password, Role role, Vote vote) {
        this(id, name, email, password, true, role, vote);
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Role role, Vote vote) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", role=" + role +
                ", registered=" + registered +
                '}';
    }
}
