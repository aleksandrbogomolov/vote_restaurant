package com.aleksandrbogomolov.vote_restaurant.repository.restaurant.datajpa;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class DishRepositoryImpl implements DishRepository {

    private final ProxyDishRepository proxy;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public DishRepositoryImpl(ProxyDishRepository proxy) {
        this.proxy = proxy;
    }

    @Override
    @Transactional
    public Dish save(Dish entity, int restaurantId) {
        if (!entity.isNew() && getOne(entity.getId(), restaurantId) == null) {
            return null;
        }
        entity.setRestaurant(entityManager.getReference(Restaurant.class, restaurantId));
        return proxy.save(entity);
    }

    @Override
    @Transactional
    public boolean delete(int id, int restaurantId) {
        return proxy.delete(id, restaurantId) != 0;
    }

    @Override
    public Dish getOne(int id, int restaurantId) {
        return proxy.findOne(id, restaurantId);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return proxy.findAll(restaurantId);
    }
}
