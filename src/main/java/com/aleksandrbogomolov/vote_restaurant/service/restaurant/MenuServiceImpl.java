package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Menu;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.MenuRepository;
import com.aleksandrbogomolov.vote_restaurant.service.BaseService;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements BaseService<Menu> {

    @Autowired
    private MenuRepository repository;

    @Override
    public Menu save(Menu entity) {
        return repository.save(entity);
    }

    @Override
    public void update(Menu entity) {
        repository.save(entity);
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

    public void clearAll() {
        repository.clearAll();
    }
}
