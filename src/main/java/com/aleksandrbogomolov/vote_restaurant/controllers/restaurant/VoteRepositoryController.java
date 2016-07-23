package com.aleksandrbogomolov.vote_restaurant.controllers.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.user.Vote;
import com.aleksandrbogomolov.vote_restaurant.service.restaurant.VoteService;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "vote")
public class VoteRepositoryController {

    @Autowired
    private VoteService service;

    @RequestMapping(value = "add/{id}", method = RequestMethod.POST)
    public void newVote(@PathVariable("id") int id) {
        Vote vote = null;
        try {
            vote = service.getOne(100001);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        if (vote != null) {
            service.delete(100001);
            if (vote.getRestaurant().getId() != id) {
                service.save(new Vote(), 100001, id);
            }
        } else {
            service.save(new Vote(), 100001, id);
        }
        logger.info("add new vote user.id={}, restaurant.id={}", 100001, id);

//        return vote;
    }

    @RequestMapping(value = "clear", method = RequestMethod.DELETE)
    public void clear() {
        service.deleteAll();
    }
}
