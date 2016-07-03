package com.aleksandrbogomolov.vote_restaurant.repository.restaurant.datajpa;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Menu;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.MenuDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DataJpaDishRepositoryImpl implements MenuDishRepository<Dish> {

    @Autowired
    private ProxyDishRepository proxy;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Dish save(Dish entity, int menu_id) {
        if (!entity.isNew() && get(entity.getId(), menu_id) == null) {
            return null;
        }
        entity.setMenu(em.getReference(Menu.class, menu_id));
        return proxy.save(entity);
    }

    @Override
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
