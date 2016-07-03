package com.aleksandrbogomolov.vote_restaurant.repository.user.datajpa;

import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {

    private static final Sort USER_NAME = new Sort("name");

    @Autowired
    private ProxyUserRepository proxy;

    @Override
    public User getByEmail(String email) {
        return proxy.findOneByEmail(email);
    }

    @Override
    public User save(User entity) {
        return proxy.save(entity);
    }

    @Override
    public boolean delete(int id) {
        return proxy.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return proxy.findOne(id);
    }

    @Override
    public List<User> getAll() {
        return proxy.findAll(USER_NAME);
    }
}
