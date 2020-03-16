package com.htp;

import com.htp.config.AppConfig;
import com.htp.domain.DoughType;
import com.htp.service.impl.DoughTypeServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
@SpringBootApplication

public class StartDoughType {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        DoughTypeServiceImpl doughTypeServiceImpl = (DoughTypeServiceImpl) context.getBean("DoughTypeRepositoryImpl");
        System.out.println("Метод DoughType findAll");
        for(DoughType doughType : doughTypeServiceImpl.findAll()){
            System.out.println(doughType.toString());
        }

        System.out.println("Метод DoughType save");
        DoughType doughType = new DoughType("Тонкое", 3.20);
        DoughType doughType1 = new DoughType("Толстое", 4.20);
        System.out.println(doughTypeServiceImpl.save(doughType));
        System.out.println(doughTypeServiceImpl.save(doughType1));

        System.out.println("Метод DoughType update");
        DoughType doughType2 = new DoughType(1L, "Тонкое с сыром", 5.10);
        System.out.println(doughTypeServiceImpl.update(doughType2));

        System.out.println("Метод DoughType delete");
        doughTypeServiceImpl.delete(2L);

        System.out.println("Метод DoughType findById");
        System.out.println(doughTypeServiceImpl.findById(1L));
    }
}
