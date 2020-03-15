package com.htp;

import com.htp.config.AppConfig;

import com.htp.service.impl.AdressServiceImpl;
import com.htp.domain.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//        UserRepositoryDao userRepositoryDao = (UserRepositoryDao) context.getBean("UserRepositoryImpl");
//        System.out.println("Метод User findAll");
//        for (User user : userRepositoryDao.findAll()) {
//            System.out.println(user.toString());
//        }
//
//        System.out.println("Метод User findById");
//        System.out.println(userRepositoryDao.findById(1L));
//
//        System.out.println("Метод User findByName");
//        System.out.println(userRepositoryDao.findByName("Mark"));
//
//        System.out.println("Метод User findContainsValue");
//        System.out.println(userRepositoryDao.findContainsValue("%u%"));
//
//        System.out.println("Метод User findIsDeleted");
//        System.out.println(userRepositoryDao.findIsDeleted(true));
//
//        System.out.println("Метод User save");
//        User user = new User("VasiaPivo1222", "Pivo2223");
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
//        OrderRepositoryDao orderRepositoryDao = (OrderRepositoryDao) context.getBean("OrderRepositoryImpl");
//        System.out.println("Метод Order findAll");
//        for(Order order : orderRepositoryDao.findAll()) {
//            System.out.println(order.toString());
//        }
//
//        System.out.println("Метод Order save");
//        Order order = new Order(13L, 1L, "+375299480901");
//        System.out.println("Метод order save прошел успешно, возвращаю order");
//        System.out.println(orderRepositoryDao.save(order));
//
//        System.out.println("Метод Order update");
//        Order secondOrder = new Order(1L, 17L, 1L,
//                new Timestamp(new Date().getTime()),"+375299480900", false);
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
//
//        MethodCallCounter methodCallCounter = (MethodCallCounter) context.getBean("MethodCallCounter");
//        System.out.println("Aspect: Map of method-counter");
//        System.out.println(methodCallCounter.getMethodCounter());
//
//        AdressRepositoryDao adressRepositoryDao = (AdressRepositoryDao) context.getBean("AdressRepositoryImpl");
        AdressServiceImpl adressServiceImpl = (AdressServiceImpl) context.getBean("AdressTest");

//        System.out.println("Метод Adress findAll");
//        for(Adress adress : adressRepositoryDao.findAll()){
//            System.out.println(adress.toString());
//        }
//
//        System.out.println("Метод Adress save");
//        Adress adress = new Adress("Mickevich", "39", 1, 2,
//                2, false);
//        Adress adress1 = new Adress("Mickevi", "30", 10, 2,
//                2, false);
        System.out.println(adressServiceImpl.findAll());
//        System.out.println(adressRepositoryDao.save(adress1));
//
//        System.out.println("Метод Adress update");
//        Adress secondAdress = new Adress(1L,"Mickevicha", "30", 10, 2,
//                2, false);
//        System.out.println(adressRepositoryDao.update(secondAdress));
//
//        System.out.println("Метод Adress delete");
//        adressRepositoryDao.delete(2L);
//
//        System.out.println("Метод Adress findById");
//        System.out.println(adressRepositoryDao.findById(1L));
//
//
//        DoughTypeRepositoryDao doughTypeRepositoryDao = (DoughTypeRepositoryDao) context.getBean("DoughTypeRepositoryImpl");
//        System.out.println("Метод DoughType findAll");
//        for(DoughType doughType : doughTypeRepositoryDao.findAll()){
//            System.out.println(doughType.toString());
//        }
//
//        System.out.println("Метод DoughType save");
//        DoughType doughType = new DoughType("Тонкое", 3.20);
//        DoughType doughType1 = new DoughType("Толстое", 4.20);
//        System.out.println(doughTypeRepositoryDao.save(doughType));
//        System.out.println(doughTypeRepositoryDao.save(doughType1));
//
//        System.out.println("Метод DoughType update");
//        DoughType doughType2 = new DoughType(1L, "Тонкое с сыром", 5.10);
//        System.out.println(doughTypeRepositoryDao.update(doughType2));
//
//        System.out.println("Метод DoughType delete");
//        doughTypeRepositoryDao.delete(2L);
//
//        System.out.println("Метод DoughType findById");
//        System.out.println(doughTypeRepositoryDao.findById(1L));
//
//
//        GoodRepositoryDao goodRepositoryDao = (GoodRepositoryDao) context.getBean("GoodRepositoryImpl");
//        System.out.println("Метод Good findAll");
//        for(Good good : goodRepositoryDao.findAll()){
//            System.out.println(good.toString());
//        }
//
//        System.out.println("Метод Good save");
//        Good good = new Good("Баварская", 12.6, 0.7,1L,1L);
//        Good good1 = new Good("Сырная", 10.8, 0.7,1L,1L);
//        System.out.println(goodRepositoryDao.save(good));
//        System.out.println(goodRepositoryDao.save(good1));
//
//        System.out.println("Метод Good update");
//        Good good2 = new Good(2L,"Баварская1",12.6, 0.7,1L,1L);
//        System.out.println(goodRepositoryDao.update(good2));
//
//        System.out.println("Метод Good delete");
//        goodRepositoryDao.delete(3L);
//
//        System.out.println("Метод Good findById");
//        System.out.println(goodRepositoryDao.findById(2L));
//
//
//        OrderGoodRepositoryDao orderGoodRepositoryDao = (OrderGoodRepositoryDao) context.getBean("OrderGoodRepositoryImpl");
//        System.out.println("Метод OrderGood findAll");
//        for(OrderGood orderGood : orderGoodRepositoryDao.findAll()){
//            System.out.println(orderGood.toString());
//        }
//
//        System.out.println("Метод OrderGood save");
//        OrderGood orderGood = new OrderGood(1L, 2L, 1);
//        OrderGood orderGood1 = new OrderGood(3L, 2L, 2);
//        System.out.println(orderGoodRepositoryDao.save(orderGood));
//        System.out.println(orderGoodRepositoryDao.save(orderGood1));
//
//        System.out.println("Метод OrderGood update");
//        OrderGood orderGood2 = new OrderGood(1L, 1L, 2L, 2);
//        System.out.println(orderGoodRepositoryDao.update(orderGood2));
//
//        System.out.println("Метод OrderGood delete");
//        orderGoodRepositoryDao.delete(2L);
//
//        System.out.println("Метод OrderGood findById");
//        System.out.println(orderGoodRepositoryDao.findById(1L));
//
//
//        RoleRepositoryDao roleRepositoryDao = (RoleRepositoryDao) context.getBean("RoleRepositoryImpl");
//        System.out.println("Метод Role findAll");
//        for(Role role : roleRepositoryDao.findAll()){
//            System.out.println(role.toString());
//        }
//
//        System.out.println("Метод Role save");
//        Role role = new Role(1L, "ROLE_ADMIN");
//        System.out.println(roleRepositoryDao.save(role));
//
//        System.out.println("Метод Role update");
//        Role role2 = new Role (6L, 24L, "ROLE_ADMIN");
//        System.out.println(roleRepositoryDao.update(role2));
//
//        System.out.println("Метод Role delete");
//        roleRepositoryDao.delete(8L);
//
//        System.out.println("Метод Role findById");
//        System.out.println(roleRepositoryDao.findById(7L));
//
//
//        SizeRepositoryDao sizeRepositoryDao = (SizeRepositoryDao) context.getBean("SizeRepositoryImpl");
//        System.out.println("Метод Size findAll");
//        for(Size size : sizeRepositoryDao.findAll()){
//            System.out.println(size.toString());
//        }
//
//        System.out.println("Метод Size save");
//        Size size = new Size(30, 6.3);
//        System.out.println(sizeRepositoryDao.save(size));
//
//        System.out.println("Метод Size update");
//        Size size1 = new Size(2L,30, 6.9);
//        System.out.println(sizeRepositoryDao.update(size1));
//
//        System.out.println("Метод Size delete");
//        sizeRepositoryDao.delete(2L);
//
//        System.out.println("Метод Size findById");
//        System.out.println(sizeRepositoryDao.findById(1L));
//
    }
}
