package com.aleksandrbogomolov.vote_restaurant.repository.restaurant.datajpa;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProxyDishRepository extends JpaRepository<Dish, Integer> {

    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=?1 AND d.restaurant.id=?2")
    int delete(int id, int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.id=?1 AND d.restaurant.id=?2")
    Dish findOne(int id, int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=?1 ORDER BY d.name")
    List<Dish> findAll(int id);
}
