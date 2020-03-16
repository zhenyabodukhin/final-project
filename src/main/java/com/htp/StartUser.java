package com.htp;

import com.htp.config.AppConfig;
import com.htp.domain.User;
import com.htp.service.impl.UserServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootApplication
public class StartUser {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserServiceImpl userService = (UserServiceImpl) context.getBean("UserServiceImpl");

        System.out.println("Метод User findAll");
        for (User user : userService.findAll()) {
            System.out.println(user.toString());
        }

        System.out.println("Метод User findById");
        System.out.println(userService.findById(1L));

        System.out.println("Метод User findByName");
        System.out.println(userService.findByName("Mark"));

        System.out.println("Метод User findContainsValue");
        System.out.println(userService.findContainsValue("%u%"));

        System.out.println("Метод User findIsDeleted");
        System.out.println(userService.findIsDeleted(true));

        System.out.println("Метод User save");
        User user = new User("VasiaPivo1222", "Pivo2223");
        System.out.println("Метод User save прошел успешно, возвращаю User'a");
        System.out.println(userService.save(user));

        System.out.println("Метод User delete");
        userService.delete(1L);

        System.out.println("Метод User update");
        User secondUser = new User(1L, "Zhenya", "juhg",
                userService.findById(1L).getCreated(),
                new Timestamp(new Date().getTime()), false);
        System.out.println(userService.update(secondUser));
    }
}
