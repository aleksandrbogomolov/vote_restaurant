package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.user.Vote;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;

import java.util.List;

public interface VoteService {

    Vote save(Vote vote, int userId, int restaurantId);

    void delete(int userId) throws NotFoundException;

    void deleteAll();

    Vote getOne(int userId) throws NotFoundException;

    List<Vote> getAll(int restaurantId);
}
