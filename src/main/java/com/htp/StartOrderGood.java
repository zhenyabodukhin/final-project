package com.htp;

import com.htp.config.AppConfig;
import com.htp.domain.OrderGood;
import com.htp.service.impl.OrderGoodServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class StartOrderGood {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        OrderGoodServiceImpl orderGoodServiceImpl = (OrderGoodServiceImpl) context.getBean("OrderGoodRepositoryImpl");
        System.out.println("Метод OrderGood findAll");
        for(OrderGood orderGood : orderGoodServiceImpl.findAll()){
            System.out.println(orderGood.toString());
        }

        System.out.println("Метод OrderGood save");
        OrderGood orderGood = new OrderGood(1L, 2L, 1);
        OrderGood orderGood1 = new OrderGood(3L, 2L, 2);
        System.out.println(orderGoodServiceImpl.save(orderGood));
        System.out.println(orderGoodServiceImpl.save(orderGood1));

        System.out.println("Метод OrderGood update");
        OrderGood orderGood2 = new OrderGood(1L, 1L, 2L, 2);
        System.out.println(orderGoodServiceImpl.update(orderGood2));

        System.out.println("Метод OrderGood delete");
        orderGoodServiceImpl.delete(2L);

        System.out.println("Метод OrderGood findById");
        System.out.println(orderGoodServiceImpl.findById(1L));
    }
}
