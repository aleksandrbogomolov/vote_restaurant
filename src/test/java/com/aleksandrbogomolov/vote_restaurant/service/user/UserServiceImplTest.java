package com.aleksandrbogomolov.vote_restaurant.service.user;

import com.aleksandrbogomolov.vote_restaurant.configuration.DataBaseConfiguration;
import com.aleksandrbogomolov.vote_restaurant.configuration.SpringWebConfiguration;
import com.aleksandrbogomolov.vote_restaurant.model.user.Role;
import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.test_data.UserTestData;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static com.aleksandrbogomolov.vote_restaurant.test_data.UserTestData.*;

@ContextConfiguration(classes = {SpringWebConfiguration.class, DataBaseConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceImplTest {

    private static Logger log = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Autowired
    private UserService<User> userService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void save() throws Exception {
        UserTestData.TestUser testUser = new UserTestData.TestUser(null, "New", "new@yandex.ru", "testPass", false, Role.USER);
        User created = userService.save(testUser.asUser());
        testUser.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, testUser, USER), userService.getAll());
        log.info(LocalDateTime.now().toString());
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailSave() throws Exception {
        userService.save(new User(null, "New", "user@yandex.ru", "testPass", false, Role.USER));
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void update() throws Exception {
        TestUser updateUser = new TestUser(USER);
        updateUser.setName("UpdateUser");
        userService.update(updateUser.asUser());
        MATCHER.assertEquals(updateUser, userService.get(USER_ID));
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void delete() throws Exception {
        userService.delete(USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN), userService.getAll());
        log.info(LocalDateTime.now().toString());
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        userService.delete(1);
        log.info(LocalDateTime.now().toString());
        throw new NotFoundException("");
    }

    @Test
    public void get() throws Exception {
        User user = userService.get(USER_ID);
        MATCHER.assertEquals(USER, user);
        log.info(LocalDateTime.now().toString());
    }

    @Test(expected = NotFoundException.class)
    public void notFoundGet() {
        userService.get(1);
        log.info(LocalDateTime.now().toString());
        throw new NotFoundException("");
    }

    @Test
    public void getByEmail() throws Exception {
        User user = userService.getByEmail(USER.getEmail());
        MATCHER.assertEquals(USER, user);
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER), userService.getAll());
        log.info(LocalDateTime.now().toString());
    }
}
