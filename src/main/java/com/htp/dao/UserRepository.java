package com.htp.dao;

import com.htp.domain.User;

import java.util.List;

public interface UserRepository {
    int save(User user);
    List<User> findAll();
    int update(User user);
    void delete(Long userId);
}
