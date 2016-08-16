package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.RestaurantRepository;
import com.aleksandrbogomolov.vote_restaurant.service.BaseService;
import com.aleksandrbogomolov.vote_restaurant.util.exception.ExceptionUtil;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements BaseService<Restaurant> {

    private final RestaurantRepository repository;

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Secured("ROLE_ADMIN")
    @Override
    public Restaurant save(Restaurant entity) {
        return repository.save(entity);
    }

    @Secured("ROLE_ADMIN")
    @Override
    public void update(Restaurant entity) {
        repository.save(entity);
    }

    @Secured("ROLE_ADMIN")
    @Override
    public void delete(int id) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Restaurant getOne(int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.getOne(id), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }
}
