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
public class DataJpaDishRepositoryImpl implements DishRepository {

    @Autowired
    private ProxyDishRepository proxy;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Dish save(Dish entity, int restaurant_id) {
        if (!entity.isNew() && get(entity.getId(), restaurant_id) == null) {
            return null;
        }
        entity.setRestaurant(em.getReference(Restaurant.class, restaurant_id));
        return proxy.save(entity);
    }

    @Override
    @Transactional
    public boolean delete(int id, int menu_id) {
        return proxy.delete(id, menu_id) != 0;
    }

    @Override
    public Dish get(int id, int menu_id) {
        return proxy.findOne(id, menu_id);
    }

    @Override
    public List<Dish> getAll(int menu_id) {
        return proxy.findAll(menu_id);
    }
}
