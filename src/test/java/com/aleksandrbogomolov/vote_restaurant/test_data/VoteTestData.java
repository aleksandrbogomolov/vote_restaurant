package com.aleksandrbogomolov.vote_restaurant.test_data;

import com.aleksandrbogomolov.vote_restaurant.matcher.ModelMatcher;
import com.aleksandrbogomolov.vote_restaurant.model.user.Vote;

import java.util.Objects;

import static com.aleksandrbogomolov.vote_restaurant.model.BaseEntity.START_SEQ;
import static com.aleksandrbogomolov.vote_restaurant.test_data.RestaurantTestData.RESTAURANT_1;
import static com.aleksandrbogomolov.vote_restaurant.test_data.UserTestData.USER;

public class VoteTestData {

    private static final int VOTE_ID = START_SEQ + 12;

    public static final Vote VOTE = new Vote(VOTE_ID, USER, RESTAURANT_1);

    public static final ModelMatcher<Vote, TestVote> MATCHER = new ModelMatcher<>(v -> ((v instanceof TestVote) ? (TestVote) v : new TestVote(v)));

    public static class TestVote extends Vote {

        public TestVote(){}

        TestVote(Vote v) {
            this(v.getId());
        }

        TestVote(Integer id) {
            super(id);
        }

        public Vote asVote() {
            return new Vote(this);
        }

        @Override
        public String toString() {
            return "Vote{" +
                    "id='" + id + '\'' +
                    "user='" + user.getName() + '\'' +
                    "restaurant='" + restaurant.getName() + '\'' +
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

            VoteTestData.TestVote testVote = (VoteTestData.TestVote) o;
            return Objects.equals(this.id, testVote.id)
                    && Objects.equals(this.user, testVote.user)
                    && Objects.equals(this.restaurant, testVote.restaurant);
        }
    }
}
