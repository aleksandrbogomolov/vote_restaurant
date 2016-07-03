package com.aleksandrbogomolov.vote_restaurant.repository.restaurant.datajpa;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProxyDishRepository extends JpaRepository<Dish, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.menu.id=:menu_id")
    int delete(@Param("id") int id, @Param("menu_id") int menu_id);

    @Query("SELECT d FROM Dish d WHERE d.id=:id AND d.menu.id=:menu_id")
    Dish findOne(@Param("id") int id, @Param("menu_id") int menu_id);

    @Query("SELECT d FROM Dish d WHERE d.menu.id=:menu_id ORDER BY d.name")
    List<Dish> findAll(@Param("menu_id") int id);
}
