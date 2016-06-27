package com.aleksandrbogomolov.vote_restaurant.test_data;

import com.aleksandrbogomolov.vote_restaurant.matcher.ModelMatcher;
import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;

import java.util.Objects;

import static com.aleksandrbogomolov.vote_restaurant.model.BaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final int RESTAURANT_ID_1 = START_SEQ + 2;
    public static final int RESTAURANT_ID_2 = START_SEQ + 3;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID_1, "Кавказ", "Днепровка");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_ID_2, "Скиф", "Энергодар");

    public static final ModelMatcher<Restaurant, TestRestaurant> MATCHER = new ModelMatcher<>(tr -> ((tr instanceof TestRestaurant) ? (TestRestaurant) tr : new TestRestaurant(tr)));

    public static class TestRestaurant extends Restaurant {

        public TestRestaurant(Restaurant r) {
            this(r.getId(), r.getName(), r.getAddress());
        }

        public TestRestaurant(String name, String address) {
            this(null, name, address);
        }

        public TestRestaurant(Integer id, String name, String address) {
            super(id, name, address);
        }

        public Restaurant asRestaurant() {
            return new Restaurant(this);
        }

        @Override
        public String toString() {
            return "Restaurant{" +
                    "id='" + id + '\'' +
                    "name='" + name + '\'' +
                    "address='" + address + '\'' +
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

            RestaurantTestData.TestRestaurant that = (RestaurantTestData.TestRestaurant) o;
            return Objects.equals(this.id, that.id)
                    && Objects.equals(this.name, that.name)
                    && Objects.equals(this.address, that.address);
        }
    }
}
