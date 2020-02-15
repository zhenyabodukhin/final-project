package com.htp.dao;

import com.htp.domain.User;

import java.util.List;

public interface UserRepository<T, K> {

    List<T> findAll();

    T save(T entity);

    T update(T entity);

    void delete(K id);

    User findById(K id);

    User findByName (String name);

    List<T> findIsDeleted (boolean value);
}
