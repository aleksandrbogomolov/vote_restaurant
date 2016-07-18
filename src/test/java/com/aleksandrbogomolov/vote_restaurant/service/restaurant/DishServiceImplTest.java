package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.service.AbstractServiceTest;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static com.aleksandrbogomolov.vote_restaurant.test_data.DishTestData.*;

public class DishServiceImplTest extends AbstractServiceTest {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private DishService service;

    @Test
    public void save() throws Exception {
        TestDish testDish = new TestDish("New", 50, 1);
        val dish = service.save(testDish.asDish(), 100002);
        testDish.setId(dish.getId());
        testDish.setRestaurant(dish.getRestaurant());
        MATCHER.assertEquals(testDish, service.getOne(100013, 100002));
    }

    @Test
    public void update() throws Exception {
        TestDish updateDish = new TestDish(DISH_1);
        updateDish.setName("Гороховый суп");
        service.update(updateDish.asDish(), 100002);
        MATCHER.assertEquals(updateDish, service.getOne(100004, 100002));
    }

    @Test(expected = NotFoundException.class)
    public void notFoundUpdate() {
        val dish = service.getOne(100004, 100002);
        service.update(dish, 100003);
    }

    @Test
    public void delete() throws Exception {
        service.delete(100004, 100002);
        MATCHER.assertCollectionEquals(sortArrays().subList(1, sortArrays().size()), service.getAll(100002));
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() {
        service.delete(1, 100002);
    }

    @Test
    public void getOne() throws Exception {
        MATCHER.assertEquals(DISH_1, service.getOne(100004, 100002));
    }

    @Test(expected = NotFoundException.class)
    public void notFoundGet() {
        service.getOne(1, 100002);
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(sortArrays(), service.getAll(100002));
    }

    private List<Dish> sortArrays() {
        val dishes = new Dish[]{DISH_1, DISH_3, DISH_5, DISH_7};
        Arrays.sort(dishes, (d1, d2) -> d1.getName().compareTo(d2.getName()));
        return Arrays.asList(dishes);
    }
}
