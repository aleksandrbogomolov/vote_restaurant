package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Menu;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.MenuDishRepository;
import com.aleksandrbogomolov.vote_restaurant.util.exception.ExceptionUtil;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuDishService<Menu> {

    @Autowired
    private MenuDishRepository<Menu> repository;

    @Override
    public Menu save(Menu entity, int restaurant_id) {
        return repository.save(entity, restaurant_id);
    }

    @Override
    public Menu update(Menu entity, int restaurant_id) {
        return ExceptionUtil.checkNotFoundWithId(repository.save(entity, restaurant_id), entity.getId());
    }

    @Override
    public void delete(int id, int restaurant_id) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, restaurant_id), id);
    }

    @Override
    public Menu get(int id, int restaurant_id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id, restaurant_id), id);
    }

    @Override
    public List<Menu> getAll(int restaurant_id) {
        return repository.getAll(restaurant_id);
    }
}
