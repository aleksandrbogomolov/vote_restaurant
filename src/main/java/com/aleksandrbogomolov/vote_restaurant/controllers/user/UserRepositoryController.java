package com.aleksandrbogomolov.vote_restaurant.controllers.user;

import com.aleksandrbogomolov.vote_restaurant.model.user.Role;
import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping(value = "profile")
public class UserRepositoryController {

    @Autowired
    private UserService<User> service;

    @RequestMapping(value = "create")
    public String create(User user) {
        user.setId(null);
        user.setRole(Role.USER);
        logger.info("create user {}", user);
        service.save(user);
        return "redirect:/restaurant/all";
    }

    @RequestMapping(value = "update")
    public String update(@RequestParam(name = "userId") int userId, Model model) {
        val user = service.getOne(userId);
        model.addAttribute("user", user);
        logger.info("update user {}", user);
        return "profile";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(User user, Model model) {
        if (user == null) return "404";
        if (!user.getPassword().equals(service.getOne(user.getId()).getPassword())) {
            logger.info("access denied");
            return "access_denied";
        } else {
            User updateUser = service.getOne(user.getId());
            if (user.getName() != null) updateUser.setName(user.getName());
            if (user.getEmail() != null) updateUser.setEmail(user.getEmail());
            service.update(updateUser);
            model.addAttribute("user", updateUser);
            logger.info("update user with id {}", updateUser.getId());
            return "profile";
        }
    }

    @RequestMapping(value = "delete")
    public String delete(@RequestParam(name = "userId") int userId) {
        logger.info("delete user with userId {}", userId);
        service.delete(userId);
        return "redirect:/";
    }

    @RequestMapping(value = "email")
    public String getByEmail(@RequestParam(name = "email") String email, @RequestParam(name = "password") String pass) {
        logger.info("get user with email {}", email);
        val user = service.getByEmail(email);
        if (user.getPassword().equals(pass)) {
            if (user.getRole().equals(Role.USER)) {
                return "redirect:/restaurant/all";
            } else if (user.getRole().equals(Role.ADMIN)) {
                return "redirect:/admin/page";
            }
        } else {
            return "access_denied";
        }
        return "404";
    }
}
