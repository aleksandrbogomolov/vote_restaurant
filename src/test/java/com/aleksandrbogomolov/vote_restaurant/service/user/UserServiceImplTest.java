package com.aleksandrbogomolov.vote_restaurant.service.user;

import com.aleksandrbogomolov.vote_restaurant.controllers.user.UserRepositoryController;
import com.aleksandrbogomolov.vote_restaurant.model.user.Role;
import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.service.AbstractServiceTest;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import lombok.val;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;
import java.util.Collections;

import static com.aleksandrbogomolov.vote_restaurant.controllers.user.UserRepositoryController.isTest;
import static com.aleksandrbogomolov.vote_restaurant.test_data.UserTestData.*;

public class UserServiceImplTest extends AbstractServiceTest {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private UserService<User> userService;

    @Before
    public void setUp() {
        isTest = true;
    }

    @After
    public void setDown() {
        isTest = false;
    }

    @Test
    public void save() throws Exception {
        TestUser testUser = new TestUser(null, "New", "new@yandex.ru", "testPass", false, Role.ROLE_USER);
        val created = userService.save(testUser.asUser());
        testUser.setId(created.getId());
        USER_MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, testUser, USER), userService.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailSave() throws Exception {
        userService.save(new User(null, "New", "user@yandex.ru", "testPass", false, Role.ROLE_USER));
    }

    @Test
    public void update() throws Exception {
        TestUser updateUser = new TestUser(USER);
        updateUser.setName("UpdateUser");
        userService.update(updateUser.asUser());
        USER_MATCHER.assertEquals(updateUser, userService.getOne(USER_ID));
    }

    @Test
    public void delete() throws Exception {
        userService.delete(USER_ID);
        USER_MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN), userService.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        userService.delete(1);
    }

    @Test
    public void getOne() throws Exception {
        User user = userService.getOne(USER_ID);
        USER_MATCHER.assertEquals(USER, user);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundGet() throws Exception {
        userService.getOne(1);
    }

    @Test
    public void getByEmail() throws Exception {
        User user = userService.getByEmail(USER.getEmail());
        USER_MATCHER.assertEquals(USER, user);
    }

    @Test
    public void getAll() throws Exception {
        USER_MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER), userService.getAll());
    }
}
