package com.aleksandrbogomolov.vote_restaurant.repository.user.datajpa;

import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProxyUserRepository extends JpaRepository<User, Integer> {

    User findOneByEmail(String email);

    @Modifying
    @Query("DELETE FROM User u WHERE u.id=?1")
    int delete(int id);
}
