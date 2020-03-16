package com.htp;

import com.htp.config.AppConfig;
import com.htp.domain.Good;
import com.htp.service.impl.GoodServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class StartGood {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        GoodServiceImpl goodServiceImpl = (GoodServiceImpl) context.getBean("GoodRepositoryImpl");
        System.out.println("Метод Good findAll");
        for(Good good : goodServiceImpl.findAll()){
            System.out.println(good.toString());
        }

        System.out.println("Метод Good save");
        Good good = new Good("Баварская", 12.6, 0.7,1L,1L);
        Good good1 = new Good("Сырная", 10.8, 0.7,1L,1L);
        System.out.println(goodServiceImpl.save(good));
        System.out.println(goodServiceImpl.save(good1));

        System.out.println("Метод Good update");
        Good good2 = new Good(2L,"Баварская1",12.6, 0.7,1L,1L);
        System.out.println(goodServiceImpl.update(good2));

        System.out.println("Метод Good delete");
        goodServiceImpl.delete(3L);

        System.out.println("Метод Good findById");
        System.out.println(goodServiceImpl.findById(2L));
    }
}
