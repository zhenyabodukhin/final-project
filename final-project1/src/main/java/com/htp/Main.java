package com.htp;

import com.htp.config.AppConfig;
import com.htp.dao.UserRepositoryDao;

import com.htp.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserRepositoryDao userRepositoryDao = (UserRepositoryDao) context.getBean("UserRepositoryImpl");

        System.out.println("Метод findAll");
        for (User user : userRepositoryDao.findAll()) {
            System.out.println(user.toString());
        }

        System.out.println("Метод findById");
        System.out.println(userRepositoryDao.findById(1L));

//        System.out.println("Метод save");
//        User user = new User("koipman", "hun266");
//        System.out.println("Метод save прошел успешно, возвращаю User'a");
//        System.out.println(userRepositoryDao.save(user));

        System.out.println("Метод delete");
        userRepositoryDao.delete(1L);


        System.out.println("Метод findByName");
        System.out.println(userRepositoryDao.findByName("Mark"));

        //System.out.println("Метод update");
        //System.out.println(userRepositoryDao.update());

    }
}
