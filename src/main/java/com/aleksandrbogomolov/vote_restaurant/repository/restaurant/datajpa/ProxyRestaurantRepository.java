package com.aleksandrbogomolov.vote_restaurant.repository.restaurant.datajpa;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProxyRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=?1")
    int delete(int id);
}
