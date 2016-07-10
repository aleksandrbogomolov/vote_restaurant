package com.aleksandrbogomolov.vote_restaurant.test_data;

import com.aleksandrbogomolov.vote_restaurant.matcher.ModelMatcher;
import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;

import java.util.Objects;

import static com.aleksandrbogomolov.vote_restaurant.model.BaseEntity.START_SEQ;

public class DishTestData {

    private static final int DISH_ID = START_SEQ + 4;

    public static final Dish DISH_1 = new Dish(DISH_ID, "Борщ", 50, 1);
    public static final Dish DISH_3 = new Dish(DISH_ID + 1, "Пюре", 30, 2);
    public static final Dish DISH_5 = new Dish(DISH_ID + 2, "Котлета", 50, 3);
    public static final Dish DISH_7 = new Dish(DISH_ID + 3, "Салат из помидор", 40, 4);
    public static final Dish DISH_2 = new Dish(DISH_ID + 4, "Солянка", 70, 1);
    public static final Dish DISH_4 = new Dish(DISH_ID + 5, "Макароны", 20, 2);
    public static final Dish DISH_6 = new Dish(DISH_ID + 6, "Бифштекс", 60, 3);
    public static final Dish DISH_8 = new Dish(DISH_ID + 7, "Компот", 10, 4);

    public static final ModelMatcher<Dish, TestDish> MATCHER = new ModelMatcher<>(d -> ((d instanceof TestDish) ? (TestDish) d : new TestDish(d)));

    public static class TestDish extends Dish {

        public TestDish(Dish d) {
            this(d.getId(), d.getName(), d.getPrice(), d.getTypeDish());
        }

        public TestDish(String name, Integer price, Integer typeDish) {
            this(null, name, price, typeDish);
        }

        TestDish(Integer id, String name, Integer price, Integer typeDish) {
            super(id, name, price, typeDish);
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

            DishTestData.TestDish testDish = (DishTestData.TestDish) o;
            return Objects.equals(this.id, testDish.id)
                    && Objects.equals(this.name, testDish.name)
                    && Objects.equals(this.price, testDish.price);
        }
    }
}
