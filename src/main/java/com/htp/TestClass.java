package com.htp;

import com.htp.dao.UserRepository;
import com.htp.dao.UserRepositoryImpl;

public class TestClass {
    public static void main(String[] args) {
        UserRepository repository = new UserRepositoryImpl();
        repository.findAll();
    }
}
