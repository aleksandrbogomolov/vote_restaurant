package com.aleksandrbogomolov.vote_restaurant.repository.restaurant.datajpa;

import com.aleksandrbogomolov.vote_restaurant.model.user.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProxyVoteRepository extends JpaRepository<Vote, Integer> {

    @Modifying
    @Query("DELETE FROM Vote v WHERE v.user.id=?1")
    int delete(int userId);

    @Modifying
    @Query("DELETE FROM Vote v")
    void deleteAll();

    @Query("SELECT v FROM Vote v WHERE v.user.id=?1")
    Vote findOne(int userId);

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id=?1")
    List<Vote> findAll(int restaurantId);
}
