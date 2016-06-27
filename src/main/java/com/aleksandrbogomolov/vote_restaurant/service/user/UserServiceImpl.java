package com.aleksandrbogomolov.vote_restaurant.service.user;

import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.repository.user.UserRepository;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return repository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
