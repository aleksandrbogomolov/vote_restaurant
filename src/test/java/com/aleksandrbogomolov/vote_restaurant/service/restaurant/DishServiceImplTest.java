package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.configuration.DataBaseConfiguration;
import com.aleksandrbogomolov.vote_restaurant.configuration.SpringWebConfiguration;
import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.test_data.DishTestData;
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
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.aleksandrbogomolov.vote_restaurant.test_data.DishTestData.*;

@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebConfiguration.class, DataBaseConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DishServiceImplTest {

    private static Logger log = LoggerFactory.getLogger(DishServiceImplTest.class);

    @Autowired
    private DishService service;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void save() throws Exception {
        DishTestData.TestDish testDish = new DishTestData.TestDish("New", 50);
        Dish dish = service.save(testDish.asDish(), 100002);
        testDish.setId(dish.getId());
        testDish.setRestaurant(dish.getRestaurant());
        MATCHER.assertEquals(testDish, service.get(100012, 100002));
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void update() throws Exception {
        DishTestData.TestDish updateDish = new DishTestData.TestDish(DISH_1);
        updateDish.setName("Гороховый суп");
        service.update(updateDish.asDish(), 100002);
        MATCHER.assertEquals(updateDish, service.get(100004, 100002));
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void notFoundUpdate() {
        exception.expect(NotFoundException.class);
        Dish dish = service.get(100004, 100002);
        service.update(dish, 100003);
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void delete() throws Exception {
        service.delete(100004,100002);
        MATCHER.assertCollectionEquals(sortArrays().subList(1, sortArrays().size()), service.getAll(100002));
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void notFoundDelete() {
        exception.expect(NotFoundException.class);
        service.delete(1, 100002);
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void get() throws Exception {
        MATCHER.assertEquals(DISH_1, service.get(100004, 100002));
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void notFoundGet() {
        exception.expect(NotFoundException.class);
        service.get(1, 100002);
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(sortArrays(), service.getAll(100002));
        log.info(LocalDateTime.now().toString());
    }

    private List<Dish> sortArrays() {
        Dish[] dishes = new Dish[]{DISH_1, DISH_3, DISH_5, DISH_7};
        Arrays.sort(dishes, (d1, d2) -> d1.getName().compareTo(d2.getName()));
        return Arrays.asList(dishes);
    }
}
