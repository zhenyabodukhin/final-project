package com.htp;

import com.htp.config.AppConfig;
import com.htp.domain.Size;
import com.htp.service.impl.SizeServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class StartSize {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        SizeServiceImpl sizeServiceImpl = (SizeServiceImpl) context.getBean("SizeRepositoryImpl");
        System.out.println("Метод Size findAll");
        for(Size size : sizeServiceImpl.findAll()){
            System.out.println(size.toString());
        }

        System.out.println("Метод Size save");
        Size size = new Size(30, 6.3);
        System.out.println(sizeServiceImpl.save(size));

        System.out.println("Метод Size update");
        Size size1 = new Size(2L,30, 6.9);
        System.out.println(sizeServiceImpl.update(size1));

        System.out.println("Метод Size delete");
        sizeServiceImpl.delete(2L);

        System.out.println("Метод Size findById");
        System.out.println(sizeServiceImpl.findById(1L));

    }
}
