package com.aleksandrbogomolov.vote_restaurant.test_data;

import com.aleksandrbogomolov.vote_restaurant.matcher.ModelMatcher;
import com.aleksandrbogomolov.vote_restaurant.model.user.Role;
import com.aleksandrbogomolov.vote_restaurant.model.user.User;

import java.time.LocalDateTime;

import static com.aleksandrbogomolov.vote_restaurant.model.BaseEntity.START_SEQ;

public class UserTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "user", "user@yandex.ru", "user", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "admin", "admin@yandex.ru", "admin", Role.ADMIN);

    public static final ModelMatcher<User, TestUser> MATCHER = new ModelMatcher<>(u -> ((u instanceof TestUser) ? (TestUser) u : new TestUser(u)));

    public static class TestUser extends User {
        public TestUser(User u) {
            this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRegistered(), u.getRole());
        }

        public TestUser(Integer id, String name, String email, String password, boolean enabled, LocalDateTime registered, Role role) {
            super(id, name, email, password, enabled, registered, role);
        }
    }

}
