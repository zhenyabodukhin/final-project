package com.htp.dao;

import com.htp.domain.Order;

import java.util.List;

public interface OrderRepository<T, K> {

    List<T> findAll();

    T save (T entity);

    T update(T entity);

    void delete (K id);

    void setOrderDone(K id);

    Order findById(K id);

    List<T> findByUserId(K id);

    List<T> findIsDone(boolean value);
}
