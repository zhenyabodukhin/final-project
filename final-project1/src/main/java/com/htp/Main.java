package com.htp;

import com.htp.dao.UserRepository;
import com.htp.dao.impl.UserRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        UserRepository repository = new UserRepositoryImpl();
        repository.findAll();

    }
}
