package com.aleksandrbogomolov.vote_restaurant.util;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.model.to.DishTo;
import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import com.aleksandrbogomolov.vote_restaurant.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Component
public class Util {

    private static final Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    private static UserService<User> service;

    private static PasswordEncoder passwordEncoder;

    @Autowired
    public Util(UserService<User> service, PasswordEncoder passwordEncoder) {
        Util.service = service;
        Util.passwordEncoder = passwordEncoder;
    }

    public static Dish createNewFromDishTo(DishTo dishTo) {
        return new Dish(dishTo.getId(), dishTo.getName(), dishTo.getPrice(), dishTo.getTypeDish());
    }

    public static String getPrincipal() {
        String email = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        }
        return email;
    }

    public static int getUserId() {
        return service.getByEmail(getPrincipal()).getId();
    }

    public static String encodePassword(String newPassword) {
        if (StringUtils.isEmpty(newPassword)) {
            return null;
        }
        if (isEncoded(newPassword)) {
            return newPassword;
        }
        return passwordEncoder.encode(newPassword);
    }

    public static boolean isEncoded(String newPassword) {
        return BCRYPT_PATTERN.matcher(newPassword).matches();
    }

    public static boolean isPasswordMatches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
