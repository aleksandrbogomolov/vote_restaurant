package com.aleksandrbogomolov.vote_restaurant.service.restaurant;

import com.aleksandrbogomolov.vote_restaurant.service.AbstractServiceTest;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

import static com.aleksandrbogomolov.vote_restaurant.test_data.VoteTestData.*;

public class VoteServiceImplTest extends AbstractServiceTest {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private VoteService service;

    @Test
    public void save() throws Exception {
        TestVote testVote = new TestVote();
        val vote = service.save(testVote.asVote(), 100001, 100003);
        testVote.setId(vote.getId());
        MATCHER.assertEquals(testVote, service.getOne(100001));
    }

    @Test
    public void delete() throws Exception {
        service.delete(100000);
        MATCHER.assertCollectionEquals(Collections.emptyList(), service.getAll(100002));
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() {
        service.delete(1);
    }

    @Test
    public void deleteAll() throws Exception {
        service.deleteAll();
        MATCHER.assertCollectionEquals(Collections.emptyList(), service.getAll(100003));
    }

    @Test
    public void getOne() throws Exception {
        MATCHER.assertEquals(VOTE, service.getOne(100000));
    }

    @Test(expected = NotFoundException.class)
    public void notFoundGetOne() {
        service.getOne(1);
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Collections.singletonList(VOTE), service.getAll(100003));
    }
}
