package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.test_data.RestaurantTestData;
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
import java.util.Arrays;
import java.util.Collections;

import static com.aleksandrbogomolov.vote_restaurant.test_data.RestaurantTestData.*;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceImplTest {

    private static Logger log = LoggerFactory.getLogger(RestaurantServiceImplTest.class);

    @Autowired
    private RestaurantService<Restaurant> service;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void save() throws Exception {
        RestaurantTestData.TestRestaurant testRestaurant = new RestaurantTestData.TestRestaurant(null, "New", "Moscow");
        Restaurant restaurant = service.save(testRestaurant.asRestaurant());
        testRestaurant.setId(restaurant.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(testRestaurant, RESTAURANT_1, RESTAURANT_2), service.getAll());
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void update() throws Exception {
        RestaurantTestData.TestRestaurant updateRestaurant = new TestRestaurant(RESTAURANT_1);
        updateRestaurant.setName("У Галины");
        service.update(updateRestaurant.asRestaurant());
        MATCHER.assertEquals(updateRestaurant, service.get(RESTAURANT_ID_1));
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void delete() throws Exception {
        service.delete(RESTAURANT_ID_1);
        MATCHER.assertCollectionEquals(Collections.singletonList(RESTAURANT_2), service.getAll());
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
        MATCHER.assertEquals(RESTAURANT_1, service.get(RESTAURANT_ID_1));
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
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT_1, RESTAURANT_2), service.getAll());
        log.info(LocalDateTime.now().toString());
    }
}