package com.htp;

import com.htp.config.AppConfig;
import com.htp.dao.OrderRepositoryDao;
import com.htp.dao.UserRepositoryDao;

import com.htp.domain.Order;
import com.htp.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserRepositoryDao userRepositoryDao = (UserRepositoryDao) context.getBean("UserRepositoryImpl");
        OrderRepositoryDao orderRepositoryDao = (OrderRepositoryDao) context.getBean("OrderRepositoryImpl");

        System.out.println("Метод User findAll");
        for (User user : userRepositoryDao.findAll(5, 0)) {
            System.out.println(user.toString());
        }
//
//        System.out.println("Метод User findById");
//        System.out.println(userRepositoryDao.findById(1L));

//        System.out.println("Метод User findByName");
//        System.out.println(userRepositoryDao.findByName("Mark"));

//        System.out.println("Метод User findContainsValue");
//        System.out.println(userRepositoryDao.findContainsValue("%u%"));

//        System.out.println("Метод User findIsDeleted");
//        System.out.println(userRepositoryDao.findIsDeleted(true));
//
//        System.out.println("Метод User save");
//        User user = new User("VasiaPivo12", "Pivo2223");
//        System.out.println("Метод User save прошел успешно, возвращаю User'a");
//        System.out.println(userRepositoryDao.save(user));
//
//        System.out.println("Метод User delete");
//        userRepositoryDao.delete(1L);
//
//        System.out.println("Метод User update");
//        User secondUser = new User(1L, "Zhenya", "juhg",
//                userRepositoryDao.findById(1L).getCreated(),
//                new Timestamp(new Date().getTime()), false);
//        System.out.println(userRepositoryDao.update(secondUser));
//
//
//
//
//        System.out.println("Метод Order findAll");
//        for(Order order : orderRepositoryDao.findAll()) {
//            System.out.println(order.toString());
//        }
//
//        System.out.println("Метод Order save");
//        Order order = new Order(12L, 1L);
//        System.out.println("Метод order save прошел успешно, возвращаю order");
//        System.out.println(orderRepositoryDao.save(order));
//
//        System.out.println("Метод Order update");
//        Order secondOrder = new Order(1L, 17L, 1L,
//                new Timestamp(new Date().getTime()), false);
//        System.out.println(orderRepositoryDao.update(secondOrder));
//
//        System.out.println("Метод Order delete");
//        orderRepositoryDao.delete(2L);
//
//        System.out.println("Метод Order setOrderDone");
//        orderRepositoryDao.setOrderDone(1L);
//
//        System.out.println("Метод Order findById");
//        System.out.println(orderRepositoryDao.findById(3L));
//
//        System.out.println("Метод Order findByUserId");
//        System.out.println(orderRepositoryDao.findByUserId(12L));
//
//        System.out.println("Метод Order findIsDone");
//        System.out.println(orderRepositoryDao.findIsDone(true));

    }
}
