package com.aleksandrbogomolov.vote_restaurant.service.user;

import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.repository.user.UserRepository;
import com.aleksandrbogomolov.vote_restaurant.util.Util;
import com.aleksandrbogomolov.vote_restaurant.util.exception.ExceptionUtil;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService<User>, UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User entity) {
        entity.setPassword(Util.encodePassword(entity.getPassword()));
        return repository.save(entity);
    }

    @Override
    public void update(User entity) {
        entity.setPassword(Util.encodePassword(entity.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(@SuppressWarnings("ParameterNameDiffersFromOverriddenParameter") String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, Collections.singletonList(user.getRole()));
    }
}
