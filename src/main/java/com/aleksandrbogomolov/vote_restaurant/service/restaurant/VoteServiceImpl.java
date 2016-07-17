package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.user.Vote;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.VoteRepository;
import com.aleksandrbogomolov.vote_restaurant.util.exception.ExceptionUtil;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository repository;

    @Override
    public Vote save(Vote vote, int userId, int restaurantId) {
        return repository.save(vote, userId, restaurantId);
    }

    @Override
    public void delete(int userId) throws NotFoundException{
        ExceptionUtil.checkNotFoundWithId(repository.delete(userId), userId);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Vote getOne(int userId) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.getOne(userId), userId);
    }

    @Override
    public List<Vote> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }
}