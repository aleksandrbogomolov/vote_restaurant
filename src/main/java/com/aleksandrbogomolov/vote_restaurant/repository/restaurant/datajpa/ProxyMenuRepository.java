package com.aleksandrbogomolov.vote_restaurant.repository.restaurant.datajpa;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProxyMenuRepository extends JpaRepository<Menu, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurant_id")
    int delete(@Param("id") int id, @Param("restaurant_id") int restaurant_id);

    @Query("SELECT m FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurant_id")
    Menu findOne(@Param("id") int id, @Param("restaurant_id") int restaurant_id);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:id ORDER BY m.registered DESC")
    List<Menu> findAll(@Param("id") int id);
}
