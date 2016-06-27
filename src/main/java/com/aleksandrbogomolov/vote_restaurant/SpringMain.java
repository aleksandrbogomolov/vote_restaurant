package com.aleksandrbogomolov.vote_restaurant;

import com.aleksandrbogomolov.vote_restaurant.controllers.user.UserRepositoryController;
import com.aleksandrbogomolov.vote_restaurant.model.user.Role;
import com.aleksandrbogomolov.vote_restaurant.model.user.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml")) {
            UserRepositoryController controller = context.getBean(UserRepositoryController.class);
            User user = controller.create(new User(null, "bob", "bob@mail.ru", "abc123", true, Role.USER));
            System.out.println("\n" + controller.get(user.getId()) + "\n");
        }
    }
}
