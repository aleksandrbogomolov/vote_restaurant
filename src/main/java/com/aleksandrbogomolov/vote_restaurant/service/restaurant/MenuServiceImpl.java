package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Menu;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.MenuDishRepository;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuDishService<Menu> {

    @Autowired
    private MenuDishRepository<Menu> repository;

    @Override
    public Menu save(Menu entity, Integer id) {
        return repository.save(entity, id);
    }

    @Override
    public void update(Menu entity, Integer id) {
        repository.save(entity, id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    public Menu get(int id) throws NotFoundException {
        return repository.get(id);
    }

    @Override
    public List<Menu> getAll() {
        return repository.getAll();
    }
}
