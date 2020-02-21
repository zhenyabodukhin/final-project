package com.htp.dao;

import com.htp.domain.User;

import java.util.List;

public interface UserRepository<T, K> {

    List<T> findAll(int limit, int offset);

    T save(T entity);

    T update(T entity);

    void delete(K id);

    User findById(K id);

    User findByName (String name);

    List<T> findContainsValue (String value);

    List<T> findIsDeleted (boolean value);
}
