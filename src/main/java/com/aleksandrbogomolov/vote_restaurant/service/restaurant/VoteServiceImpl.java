package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.user.Vote;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.VoteRepository;
import com.aleksandrbogomolov.vote_restaurant.util.exception.ExceptionUtil;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import com.aleksandrbogomolov.vote_restaurant.util.exception.TimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;

    @Autowired
    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vote save(Vote vote, int userId, int restaurantId) throws TimeException {
        if (LocalTime.now().isBefore(LocalTime.of(7, 0)) || LocalTime.now().isAfter(LocalTime.of(11, 0))) {
            throw new TimeException("Time to vote from 07:00 to 11:00");
        } else {
            return repository.save(vote, userId, restaurantId);
        }
    }

    @Override
    public void delete(int userId) throws NotFoundException {
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
