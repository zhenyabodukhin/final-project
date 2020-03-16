package com.htp;

import com.htp.config.AppConfig;
import com.htp.domain.Adress;
import com.htp.service.impl.AdressServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class StartAdress {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        AdressServiceImpl adressServiceImpl = (AdressServiceImpl) context.getBean("AdressServiceImpl");

        System.out.println("Метод Adress findAll");
        for(Adress adress : adressServiceImpl.findAll()){
            System.out.println(adress.toString());
        }

        System.out.println("Метод Adress save");
        Adress adress = new Adress("Mickevich", "39", 1, 2,
                2, false);
        Adress adress1 = new Adress("Mickevi", "30", 10, 2,
                2, false);
        System.out.println(adressServiceImpl.findAll());
        System.out.println(adressServiceImpl.save(adress1));

        System.out.println("Метод Adress update");
        Adress secondAdress = new Adress(1L,"Mickevicha", "30", 10, 2,
                2, false);
        System.out.println(adressServiceImpl.update(secondAdress));

        System.out.println("Метод Adress delete");
        adressServiceImpl.delete(2L);

        System.out.println("Метод Adress findById");
        System.out.println(adressServiceImpl.findById(1L));
    }
}
