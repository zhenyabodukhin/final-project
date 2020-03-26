package com.htp.service.impl;


import com.htp.domain.Order;
import com.htp.repository.OrderRepository;
import com.htp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("OrderServiceImpl")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public Order update(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void setOrderDone(Long id) {
        orderRepository.setOrderDone(id);
    }

    @Override
    public List<Order> findByUserId(Long id) {
        return orderRepository.findByUserId(id);
    }

    @Override
    public List<Order> findIsDone(Boolean value) {
        return orderRepository.findIsDone(value);
    }

    @Override
    public List<Order> findByAddressId(Long id) {
        return orderRepository.findByAddressId(id);
    }

    @Override
    public List<Order> findByPhoneNumber(String phoneNumber) {
        return orderRepository.findByPhoneNumber(phoneNumber);
    }
}
