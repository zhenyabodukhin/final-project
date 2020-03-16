package com.htp;

import com.htp.config.AppConfig;
import com.htp.domain.Order;
import com.htp.service.impl.OrderServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootApplication
public class StartOrder {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        OrderServiceImpl orderServiceImpl = (OrderServiceImpl) context.getBean("OrderServiceImpl");
        System.out.println("Метод Order findAll");
        for(Order order : orderServiceImpl.findAll()) {
            System.out.println(order.toString());
        }

        System.out.println("Метод Order save");
        Order order = new Order(13L, 1L, "+375299480901");
        System.out.println("Метод order save прошел успешно, возвращаю order");
        System.out.println(orderServiceImpl.save(order));

        System.out.println("Метод Order update");
        Order secondOrder = new Order(1L, 17L, 1L,
                new Timestamp(new Date().getTime()),"+375299480900", false);
        System.out.println(orderServiceImpl.update(secondOrder));

        System.out.println("Метод Order delete");
        orderServiceImpl.delete(2L);

        System.out.println("Метод Order setOrderDone");
        orderServiceImpl.setOrderDone(1L);

        System.out.println("Метод Order findById");
        System.out.println(orderServiceImpl.findById(3L));

        System.out.println("Метод Order findByUserId");
        System.out.println(orderServiceImpl.findByUserId(12L));

        System.out.println("Метод Order findIsDone");
        System.out.println(orderServiceImpl.findIsDone(true));
    }
}
