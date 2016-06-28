package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.aleksandrbogomolov.vote_restaurant.test_data.DishTestData.*;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DishServiceImplTest {

    private static Logger log = LoggerFactory.getLogger(DishServiceImplTest.class);

    @Autowired
    private MenuDishService<Dish> service;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void save() throws Exception {
        DishTestData.TestDish testDish = new DishTestData.TestDish("New", 50);
        Dish dish = service.save(testDish.asDish(), 100004);
        testDish.setId(dish.getId());
        testDish.setMenu(dish.getMenu());
        MATCHER.assertEquals(testDish, service.get(100015));
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void update() throws Exception {
        DishTestData.TestDish updateDish = new DishTestData.TestDish(DISH_1);
        updateDish.setName("Гороховый суп");
        service.update(updateDish.asDish(), 100004);
        MATCHER.assertEquals(updateDish, service.get(100007));
        log.info(LocalDateTime.now().toString());
    }

    @Test
    public void delete() throws Exception {
        service.delete(100013);
        MATCHER.assertCollectionEquals(sortArrays().subList(1, sortArrays().size()), service.getAll());
        log.info(LocalDateTime.now().toString());
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() {
        service.delete(1);
        log.info(LocalDateTime.now().toString());
        throw new NotFoundException("");
    }

    @Test
    public void get() throws Exception {
        MATCHER.assertEquals(DISH_1, service.get(100007));
        log.info(LocalDateTime.now().toString());
    }

    @Test(expected = NotFoundException.class)
    public void notFoundGet() {
        service.get(1);
        log.info(LocalDateTime.now().toString());
        throw new NotFoundException("");
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(sortArrays(), service.getAll());
        log.info(LocalDateTime.now().toString());
    }

    private List<Dish> sortArrays() {
        Dish[] dishes = new Dish[]{DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8};
        Arrays.sort(dishes, (d1, d2) -> d1.getName().compareTo(d2.getName()));
        return Arrays.asList(dishes);
    }
}
