package com.htp.service;

import com.htp.domain.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order save (Order order);

    Order update (Order order);

    void delete (Long id);

    Order findById(Long id);
}
