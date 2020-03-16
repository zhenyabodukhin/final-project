package com.htp;

import com.htp.config.AppConfig;
import com.htp.domain.Role;
import com.htp.service.impl.RoleServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class StartRole {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        RoleServiceImpl roleServiceImpl = (RoleServiceImpl) context.getBean("RoleRepositoryImpl");
        System.out.println("Метод Role findAll");
        for(Role role : roleServiceImpl.findAll()){
            System.out.println(role.toString());
        }

        System.out.println("Метод Role save");
        Role role = new Role(1L, "ROLE_ADMIN");
        System.out.println(roleServiceImpl.save(role));

        System.out.println("Метод Role update");
        Role role2 = new Role (6L, 24L, "ROLE_ADMIN");
        System.out.println(roleServiceImpl.update(role2));

        System.out.println("Метод Role delete");
        roleServiceImpl.delete(8L);

        System.out.println("Метод Role findById");
        System.out.println(roleServiceImpl.findById(7L));
    }
}
