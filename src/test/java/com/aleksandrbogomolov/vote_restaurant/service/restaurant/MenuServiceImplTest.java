package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Menu;
import com.aleksandrbogomolov.vote_restaurant.test_data.MenuTestData;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

import static com.aleksandrbogomolov.vote_restaurant.test_data.MenuTestData.*;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MenuServiceImplTest {

    private static Logger log = LoggerFactory.getLogger(MenuServiceImplTest.class);

    @Autowired
    private AdditionalService<Menu> service;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void save() throws Exception {
        MenuTestData.TestMenu testMenu = new MenuTestData.TestMenu(null, LocalDateTime.of(2016, Month.JUNE, 28, 9, 30));
        Menu menu = service.save(testMenu.asMenu(), 100002);
        testMenu.setId(menu.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(testMenu, MENU_2, MENU_3, MENU_1), service.getAll());
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void update() throws Exception {
        TestMenu updateMenu = new TestMenu(MENU_1);
        updateMenu.setRegistered(LocalDateTime.of(2016, Month.JUNE, 25, 9, 0));
        service.update(updateMenu.asMenu(), 100002);
        MATCHER.assertEquals(updateMenu, service.get(100004));
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void delete() throws Exception {
        service.delete(100004);
        MATCHER.assertCollectionEquals(Arrays.asList(MENU_2, MENU_3), service.getAll());
        log.info(LocalDateTime.now().toString());
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
        log.info(LocalDateTime.now().toString());
        throw new NotFoundException("");
    }

    @Test
    public void get() throws Exception {
        MATCHER.assertEquals(MENU_1, service.get(100004));
        log.info(LocalDateTime.now().toString());
    }

    @Test(expected = NotFoundException.class)
    public void notFoundGet() throws Exception {
        service.get(1);
        log.info(LocalDateTime.now().toString());
        throw new NotFoundException("");
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MENU_2, MENU_3, MENU_1), service.getAll());
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void clearAll() throws Exception {
        service.clearAll();
        MATCHER.assertCollectionEquals(Collections.emptyList(), service.getAll());
        log.info(LocalDateTime.now().toString());
    }
}