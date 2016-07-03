package com.aleksandrbogomolov.vote_restaurant.repository.restaurant.datajpa;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Menu;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.MenuDishRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaMenuRepositoryImpl implements MenuDishRepository<Menu> {

    @Override
    public Menu save(Menu entity, int restaurant_id) {
        return null;
    }

    @Override
    public boolean delete(int id, int restaurant_id) {
        return false;
    }

    @Override
    public Menu get(int id, int restaurant_id) {
        return null;
    }

    @Override
    public List<Menu> getAll(int restaurant_id) {
        return null;
    }
}
