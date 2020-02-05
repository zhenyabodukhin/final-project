package com.htp.testcontr;

import com.htp.dao.UserRepository;
import com.htp.dao.UserRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        UserRepository repository = new UserRepositoryImpl();
        repository.findAll();

    }
}
