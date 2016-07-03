package com.aleksandrbogomolov.vote_restaurant.repository.restaurant.datajpa;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Menu;
import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.MenuDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DataJpaMenuRepositoryImpl implements MenuDishRepository<Menu> {

    @Autowired
    private ProxyMenuRepository proxy;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Menu save(Menu entity, int restaurant_id) {
        if (!entity.isNew() && get(entity.getId(), restaurant_id) == null) {
            return null;
        }
        entity.setRestaurant(em.getReference(Restaurant.class, restaurant_id));
        return proxy.save(entity);
    }

    @Override
    public boolean delete(int id, int restaurant_id) {
        return proxy.delete(id, restaurant_id) != 0;
    }

    @Override
    public Menu get(int id, int restaurant_id) {
        return proxy.findOne(id, restaurant_id);
    }

    @Override
    public List<Menu> getAll(int restaurant_id) {
        return proxy.findAll(restaurant_id);
    }
}
