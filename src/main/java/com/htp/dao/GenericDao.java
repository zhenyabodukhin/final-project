package com.htp.dao;


import java.util.List;

public interface GenericDao<T, K> {

    List<T> findAll();

    T save (T entity);

    T update(T entity);

    void delete (K id);

    T findById(K id);
}
