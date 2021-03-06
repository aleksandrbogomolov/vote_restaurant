package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.service.AbstractServiceTest;
import com.aleksandrbogomolov.vote_restaurant.service.BaseService;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;

import static com.aleksandrbogomolov.vote_restaurant.test_data.RestaurantTestData.*;

public class RestaurantServiceImplTest extends AbstractServiceTest {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private BaseService<Restaurant> service;

    @Test
    public void save() throws Exception {
        TestRestaurant testRestaurant = new TestRestaurant(null, "New", "Moscow");
        val restaurant = service.save(testRestaurant.asRestaurant());
        testRestaurant.setId(restaurant.getId());
        RESTAURANT_MATCHER.assertCollectionEquals(Arrays.asList(testRestaurant, RESTAURANT_1, RESTAURANT_2), service.getAll());
    }

    @Test
    public void update() throws Exception {
        TestRestaurant updateRestaurant = new TestRestaurant(RESTAURANT_1);
        updateRestaurant.setName("У Галины");
        service.update(updateRestaurant.asRestaurant());
        RESTAURANT_MATCHER.assertEquals(updateRestaurant, service.getOne(RESTAURANT_ID));
    }

    @Test
    public void delete() throws Exception {
        service.delete(RESTAURANT_ID);
        RESTAURANT_MATCHER.assertCollectionEquals(Collections.singletonList(RESTAURANT_2), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void getOne() throws Exception {
        RESTAURANT_MATCHER.assertEquals(RESTAURANT_1, service.getOne(RESTAURANT_ID));
    }

    @Test(expected = NotFoundException.class)
    public void notFoundGet() throws Exception {
        service.getOne(1);
    }

    @Test
    public void getAll() throws Exception {
        RESTAURANT_MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT_1, RESTAURANT_2), service.getAll());
    }
}
