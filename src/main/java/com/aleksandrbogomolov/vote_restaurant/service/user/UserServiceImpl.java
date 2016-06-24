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
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        userRepository.delete(id);
    }

    @Override
    public User get(int id) {
        return userRepository.get(id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return userRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }
}
