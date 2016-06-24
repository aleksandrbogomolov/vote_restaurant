package com.aleksandrbogomolov.vote_restaurant.model.user;

import com.aleksandrbogomolov.vote_restaurant.model.NamedEntity;

import java.time.LocalDate;

public class User extends NamedEntity {

    private String email;

    private String password;

    private LocalDate registered;

    private boolean enabled = true;

    private Role role;

    public User() {
    }

    public User(Integer id, String name, String email, String password, boolean enabled, LocalDate registered, Role role) {
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

    public LocalDate getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDate registered) {
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
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", registered=" + registered +
                ", role=" + role +
                '}';
    }
}
