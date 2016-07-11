package com.aleksandrbogomolov.vote_restaurant.repository.restaurant.datajpa;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.model.user.Vote;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class VoteRepositoryIml implements VoteRepository {

    @Autowired
    private ProxyVoteRepository proxy;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId, int restaurantId) {
        vote.setUser(entityManager.getReference(User.class, userId));
        vote.setRestaurant(entityManager.getReference(Restaurant.class, restaurantId));
        return proxy.save(vote);
    }

    @Override
    @Transactional
    public boolean delete(int userId) {
        return proxy.delete(userId) != 0;
    }

    @Override
    @Transactional
    public void deleteAll() {
        proxy.deleteAll();
    }

    @Override
    public Vote getOne(int userId) {
        return proxy.findOne(userId);
    }

    @Override
    public List<Vote> getAll(int restaurantId) {
        return proxy.findAll(restaurantId);
    }
}
