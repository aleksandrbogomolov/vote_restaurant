package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.repository.BaseRepository;
import com.aleksandrbogomolov.vote_restaurant.service.BaseService;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements BaseService<Restaurant> {

    @Autowired
    private BaseRepository<Restaurant> repository;

    @Override
    public Restaurant save(Restaurant entity) {
        return repository.save(entity);
    }

    @Override
    public void update(Restaurant entity) {
        repository.save(entity);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return repository.get(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }
}
