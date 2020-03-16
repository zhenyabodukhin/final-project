package com.htp.service;

import com.htp.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save (User user);

    User update (User user);

    void delete (Long id);

    User findById(Long id);

    User findByName (String name);

    List<User> findContainsValue (String value);

    List<User> findIsDeleted (boolean value);
}
