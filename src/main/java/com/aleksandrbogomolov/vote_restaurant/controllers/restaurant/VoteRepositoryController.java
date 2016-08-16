package com.aleksandrbogomolov.vote_restaurant.controllers.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.user.Vote;
import com.aleksandrbogomolov.vote_restaurant.service.restaurant.VoteService;
import com.aleksandrbogomolov.vote_restaurant.util.Util;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "vote")
public class VoteRepositoryController {

    private final VoteService service;

    @Autowired
    public VoteRepositoryController(VoteService service) {
        this.service = service;
    }

    @SuppressWarnings("NonBooleanMethodNameMayNotStartWithQuestion")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void addVote(@PathVariable("id") int id) {
        Vote vote = null;
        val userId = Util.getUserId();
        try {
            vote = service.getOne(userId);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        if (vote != null) {
            service.delete(userId);
            if (vote.getRestaurant().getId() != id) {
                service.save(new Vote(), userId, id);
            }
        } else {
            service.save(new Vote(), userId, id);
        }
        logger.info("add vote user.id={}, restaurant.id={}", userId, id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void clear() {
        service.deleteAll();
    }
}
