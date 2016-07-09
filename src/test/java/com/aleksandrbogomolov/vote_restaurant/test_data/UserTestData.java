package com.aleksandrbogomolov.vote_restaurant.test_data;

import com.aleksandrbogomolov.vote_restaurant.matcher.ModelMatcher;
import com.aleksandrbogomolov.vote_restaurant.model.user.Role;
import com.aleksandrbogomolov.vote_restaurant.model.user.User;

import java.util.Objects;

import static com.aleksandrbogomolov.vote_restaurant.model.BaseEntity.START_SEQ;

public class UserTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@yandex.ru", "admin", Role.ADMIN);

    public static final ModelMatcher<User, TestUser> USER_MATCHER = new ModelMatcher<>(u -> ((u instanceof TestUser) ? (TestUser) u : new TestUser(u)));

    public static class TestUser extends User {

        public TestUser(User u) {
            this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRole());
        }

        public TestUser(String name, String email, String password, Role role) {
            this(null, name, email, password, true, role);
        }

        public TestUser(Integer id, String name, String email, String password, boolean enabled, Role role) {
            super(id, name, email, password, enabled, role);
        }

        public User asUser() {
            return new User(this);
        }

        @Override
        public String toString() {
            return "User{" +
                    "id='" + id + '\'' +
                    "name='" + name + '\'' +
                    "email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", enabled=" + enabled +
                    ", role=" + role +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            TestUser that = (TestUser) o;
            return Objects.equals(this.password, that.password)
                    && Objects.equals(this.id, that.id)
                    && Objects.equals(this.name, that.name)
                    && Objects.equals(this.email, that.email)
                    && Objects.equals(this.enabled, that.enabled)
                    && Objects.equals(this.role, that.role);
        }
    }

}
