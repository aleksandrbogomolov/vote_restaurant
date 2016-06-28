package com.aleksandrbogomolov.vote_restaurant.test_data;

import com.aleksandrbogomolov.vote_restaurant.matcher.ModelMatcher;
import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Menu;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

import static com.aleksandrbogomolov.vote_restaurant.model.BaseEntity.START_SEQ;

public class MenuTestData {

    public static final int MENU_ID = START_SEQ + 4;

    public static final Menu MENU_1 = new Menu(MENU_ID, LocalDateTime.of(2016, Month.JUNE, 26, 9, 0));
    public static final Menu MENU_2 = new Menu(MENU_ID + 1, LocalDateTime.of(2016, Month.JUNE, 27, 9, 0));
    public static final Menu MENU_3 = new Menu(MENU_ID + 2, LocalDateTime.of(2016, Month.JUNE, 27, 8, 30));

    public static final ModelMatcher<Menu, TestMenu> MATCHER = new ModelMatcher<>(m -> ((m instanceof TestMenu) ? (TestMenu) m : new TestMenu(m)));

    public static class TestMenu extends Menu {

        public TestMenu(Menu m) {
            this(m.getId(), m.getRegistered());
        }

        public TestMenu(LocalDateTime dateTime) {
            this(null, dateTime);
        }

        public TestMenu(Integer id, LocalDateTime dateTime) {
            super(id, dateTime);
        }

        public Menu asMenu() {
            return new Menu(this);
        }

        @Override
        public String toString() {
            return "Menu{" +
                    "id='" + id + '\'' +
                    "registered='" + registered + '\'' +
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

            MenuTestData.TestMenu that = (MenuTestData.TestMenu) o;
            return Objects.equals(this.id, that.id)
                    && Objects.equals(this.registered, that.registered);
        }
    }
}
