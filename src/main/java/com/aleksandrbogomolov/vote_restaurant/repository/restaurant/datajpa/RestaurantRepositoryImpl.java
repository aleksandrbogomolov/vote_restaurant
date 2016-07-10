package com.aleksandrbogomolov.vote_restaurant.repository.restaurant.datajpa;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private static final Sort RESTAURANT_NAME = new Sort("name");

    @Autowired
    private ProxyRestaurantRepository proxy;

    @Override
    @Transactional
    public Restaurant save(Restaurant entity) {
        return proxy.save(entity);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return proxy.delete(id) != 0;
    }

    @Override
    public Restaurant getOne(int id) {
        return proxy.findOne(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return proxy.findAll(RESTAURANT_NAME);
    }
}
