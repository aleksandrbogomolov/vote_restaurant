package com.aleksandrbogomolov.vote_restaurant.controllers.restaurant;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(value = "restaurant")
public class RestaurantRepositoryController {

    private final BaseService<Restaurant> service;

    @Autowired
    public RestaurantRepositoryController(BaseService<Restaurant> service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllRestaurants() {
        return service.getAll()
                .stream()
                .sorted((r1, r2) -> Integer.compare(r2.getVotes().size(), r1.getVotes().size()))
                .collect(Collectors.toList());
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createOrUpdate(@Valid Restaurant restaurant) {
        if (restaurant.isNew()) {
            logger.info("create restaurant {}", restaurant);
            service.save(restaurant);
        } else {
            logger.info("update restaurant with id {}", restaurant.getId());
            service.update(restaurant);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        logger.info("delete restaurant with id {}", id);
        service.delete(id);
    }
}
