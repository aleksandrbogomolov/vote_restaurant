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

@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u WHERE u.email=:email"),
        @NamedQuery(name = User.GET_ALL, query = "SELECT u FROM User  u ORDER BY u.name")
})
@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends NamedEntity {

    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL = "User.getByEmail";
    public static final String GET_ALL = "User.getAll";

    @NotEmpty
    @Email
    @Column(name = "email", unique = true)
    protected String email;

    @NotEmpty
    @Length(min = 5)
    @Column(name = "password")
    protected String password;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private Date registered = new Date();

    @NotNull
    @Column(name = "enabled")
    protected boolean enabled = true;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    protected Role role;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRole());
    }

    public User(Integer id, String name, String email, String password, Role role) {
        this(id, name, email, password, true, role);
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Role role) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
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
                '}';
    }
}
