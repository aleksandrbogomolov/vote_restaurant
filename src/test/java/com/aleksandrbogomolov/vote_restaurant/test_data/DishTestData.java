package com.aleksandrbogomolov.vote_restaurant.test_data;

import com.aleksandrbogomolov.vote_restaurant.matcher.ModelMatcher;
import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;

import java.util.Objects;

import static com.aleksandrbogomolov.vote_restaurant.model.BaseEntity.START_SEQ;

public class DishTestData {

    public static final int DISH_ID = START_SEQ + 4;

    public static final Dish DISH_1 = new Dish(DISH_ID, "Борщ", 50);
    public static final Dish DISH_3 = new Dish(DISH_ID + 1, "Пюре", 30);
    public static final Dish DISH_5 = new Dish(DISH_ID + 2, "Котлета", 50);
    public static final Dish DISH_7 = new Dish(DISH_ID + 3, "Салат из помидор", 40);
    public static final Dish DISH_2 = new Dish(DISH_ID + 4, "Солянка", 70);
    public static final Dish DISH_4 = new Dish(DISH_ID + 5, "Макароны", 20);
    public static final Dish DISH_6 = new Dish(DISH_ID + 6, "Бифштекс", 60);
    public static final Dish DISH_8 = new Dish(DISH_ID + 7, "Компот", 10);

    public static final ModelMatcher<Dish, TestDish> MATCHER = new ModelMatcher<>(d -> ((d instanceof TestDish) ? (TestDish) d : new TestDish(d)));

    public static class TestDish extends Dish {

        public TestDish(Dish d) {
            this(d.getId(), d.getName(), d.getPrice());
        }

        public TestDish(String name, Integer price) {
            this(null, name, price);
        }

        public TestDish(Integer id, String name, Integer price) {
            super(id, name, price);
        }

        public Dish asDish() {
            return new Dish(this);
        }

        @Override
        public String toString() {
            return "Dish{" +
                    "id='" + id + '\'' +
                    "name='" + name + '\'' +
                    "price='" + price + '\'' +
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

            DishTestData.TestDish that = (DishTestData.TestDish) o;
            return Objects.equals(this.id, that.id)
                    && Objects.equals(this.name, that.name)
                    && Objects.equals(this.price, that.price);
        }
    }
}
