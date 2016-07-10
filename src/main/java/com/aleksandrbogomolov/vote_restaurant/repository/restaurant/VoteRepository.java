package com.aleksandrbogomolov.vote_restaurant.repository.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.user.Vote;

import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, int userId, int restaurantId);

    boolean delete(int userId);

    void deleteAll();

    Vote getOne(int userId);

    List<Vote> getAll(int restaurantId);
}
