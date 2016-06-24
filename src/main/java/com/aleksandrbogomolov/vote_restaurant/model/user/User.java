package com.aleksandrbogomolov.vote_restaurant.model.user;

import com.aleksandrbogomolov.vote_restaurant.model.NamedEntity;
import com.aleksandrbogomolov.vote_restaurant.util.LocalDateTimePersistenceConverter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u WHERE u.email=:email"),
        @NamedQuery(name = User.GET_ALL, query = "SELECT u FROM User  u ORDER BY u.name")
})
@Entity
@Table(name = "users")
public class User extends NamedEntity {

    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL = "User.getByEmail";
    public static final String GET_ALL = "User.getAll";

    @NotEmpty
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty
    @Length(min = 6)
    @Column(name = "password")
    private String password;

    @NotNull
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private LocalDateTime registered;

    @NotNull
    @Column(name = "enabled")
    private boolean enabled = true;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public User() {
    }

    public User(Integer id, String name, String email, String password, boolean enabled, LocalDateTime registered, Role role) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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
                ", registered=" + registered +
                ", role=" + role +
                '}';
    }
}
