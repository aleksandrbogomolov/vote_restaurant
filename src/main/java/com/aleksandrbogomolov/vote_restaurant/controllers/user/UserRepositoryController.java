package com.aleksandrbogomolov.vote_restaurant.controllers.user;

import com.aleksandrbogomolov.vote_restaurant.model.user.Role;
import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserRepositoryController {

    private static Logger logger = LoggerFactory.getLogger(UserRepositoryController.class);

    @Autowired
    private UserService<User> service;

    @ModelAttribute("users")
    public List<User> getAllUsers() {
        return service.getAll();
    }

    public User create(User user) {
        user.setId(null);
        logger.info("create user {}", user);
        service.save(user);
        return user;
    }

    @RequestMapping(value = "update")
    public String update(@RequestParam(name = "userId") int userId, Model model) {
        User user = service.getOne(userId);
        model.addAttribute("user", user);
        logger.info("update user {}", user);
        return "user";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(User user, HttpServletRequest request) {
        if (user.getId() != null) {
            if (!user.getPassword().equals(service.getOne(user.getId()).getPassword())) {
                return "user";
            } else {
                User updateUser = service.getOne(user.getId());
                if (user.getName() != null) updateUser.setName(user.getName());
                if (user.getEmail() != null) updateUser.setEmail(user.getEmail());
                service.update(updateUser);
            }
        } else {
            user.setRole(Role.USER);
            service.save(user);
            return "redirect:/restaurant/all";
        }
        return "404";
    }

    @RequestMapping(value = "delete")
    public String delete(@RequestParam(name = "userId") int userId) {
        logger.info("delete user with userId {}", userId);
        service.delete(userId);
        return "redirect:/";
    }

    @RequestMapping(value = "one")
    public User getOne(HttpServletRequest request) {
        logger.info("getOne user with userId {}");
        return null;
    }

    @RequestMapping(value = "email")
    public String getByEmail(@RequestParam(name = "email") String email, @RequestParam(name = "password") String pass) {
        logger.info("getOne user with email {}", email);
        User user = service.getByEmail(email);
        if (user.getPassword().equals(pass)) {
            if (user.getRole().equals(Role.USER)) {
                return "redirect:/restaurant/all";
            } else if (user.getRole().equals(Role.ADMIN)) {
                return "admin";
            }
        } else {
            return "access_denied";
        }
        return "404";
    }

    @RequestMapping(value = "users")
    public String getAll(User user) {
        logger.info("get all users");
        return "user_page";
    }
}
