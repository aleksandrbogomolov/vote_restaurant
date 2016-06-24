package com.aleksandrbogomolov.vote_restaurant.model;

public class NamedIEntity extends BaseEntity {

    protected String name;

    public NamedIEntity() {
    }

    public NamedIEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NamedIEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
