package com.aleksandrbogomolov.vote_restaurant.repository.user.datajpa;

import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {

    private static final Sort USER_NAME = new Sort("name");

    @Autowired
    private ProxyUserRepository proxy;

    @Override
    @Transactional
    public User save(User entity) {
        return proxy.save(entity);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return proxy.delete(id) != 0;
    }

    @Override
    public User getOne(int id) {
        return proxy.findOne(id);
    }

    @Override
    public User getByEmail(String email) {
        return proxy.findOneByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return proxy.findAll(USER_NAME);
    }
}
