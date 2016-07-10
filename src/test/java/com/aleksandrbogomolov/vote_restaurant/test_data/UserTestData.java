package com.aleksandrbogomolov.vote_restaurant.test_data;

import com.aleksandrbogomolov.vote_restaurant.matcher.ModelMatcher;
import com.aleksandrbogomolov.vote_restaurant.model.user.Role;
import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.model.user.Vote;

import java.util.Objects;

import static com.aleksandrbogomolov.vote_restaurant.model.BaseEntity.START_SEQ;

public class UserTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.USER, null);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@yandex.ru", "admin", Role.ADMIN, null);

    public static final ModelMatcher<User, TestUser> USER_MATCHER = new ModelMatcher<>(u -> ((u instanceof TestUser) ? (TestUser) u : new TestUser(u)));

    public static class TestUser extends User {

        public TestUser(User u) {
            this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRole(), u.getVote());
        }

        public TestUser(String name, String email, String password, Role role, Vote vote) {
            this(null, name, email, password, true, role, vote);
        }

        public TestUser(Integer id, String name, String email, String password, boolean enabled, Role role, Vote vote) {
            super(id, name, email, password, enabled, role, vote);
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
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            TestUser testUser = (TestUser) obj;
            return Objects.equals(this.password, testUser.password)
                    && Objects.equals(this.id, testUser.id)
                    && Objects.equals(this.name, testUser.name)
                    && Objects.equals(this.email, testUser.email)
                    && Objects.equals(this.enabled, testUser.enabled)
                    && Objects.equals(this.role, testUser.role);
        }
    }
}
