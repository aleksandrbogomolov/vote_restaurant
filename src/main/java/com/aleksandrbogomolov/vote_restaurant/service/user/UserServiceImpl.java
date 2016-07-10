package com.aleksandrbogomolov.vote_restaurant.service.user;

import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.repository.user.UserRepository;
import com.aleksandrbogomolov.vote_restaurant.util.exception.ExceptionUtil;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService<User> {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public void update(User entity) {
        repository.save(entity);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User getOne(int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.getOne(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return ExceptionUtil.checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
