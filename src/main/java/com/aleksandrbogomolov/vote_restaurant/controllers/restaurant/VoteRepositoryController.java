package com.aleksandrbogomolov.vote_restaurant.controllers.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.user.Role;
import com.aleksandrbogomolov.vote_restaurant.model.user.Vote;
import com.aleksandrbogomolov.vote_restaurant.service.restaurant.VoteService;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping(value = "vote")
public class VoteRepositoryController {

    @Autowired
    private VoteService service;

    @RequestMapping(value = "add")
    public String newVote(@RequestParam(name = "restaurant") int restaurantId) {
        Vote vote = null;
        try {
            vote = service.getOne(100001);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        if (vote != null && vote.getUser().getRole() == Role.USER) {
            service.delete(100001);
            if (vote.getRestaurant().getId() != restaurantId) {
                service.save(new Vote(), 100001, restaurantId);
            }
        } else {
            service.save(new Vote(), 100001, restaurantId);
        }
        logger.info("add new vote user.id={}, restaurant.id={}", 100001, restaurantId);

        return "redirect:/restaurant/all";
    }

    @RequestMapping(value = "clear")
    public String clear() {
        service.deleteAll();
        return "redirect:/admin/page";
    }
}
