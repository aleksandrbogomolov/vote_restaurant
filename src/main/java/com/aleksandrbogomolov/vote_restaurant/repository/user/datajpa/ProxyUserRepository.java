package com.aleksandrbogomolov.vote_restaurant.repository.user.datajpa;

import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ProxyUserRepository extends JpaRepository<User, Integer> {

    User findOneByEmail(String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id=?1")
    int delete(@Param("id") int id);
}
