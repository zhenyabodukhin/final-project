package com.htp.service.impl;


import com.htp.domain.Order;
import com.htp.exception.EntityNotFoundException;
import com.htp.repository.OrderRepository;
import com.htp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Order update(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(Long id) {
        if (orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(Order.class, id);
        }
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> result = orderRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(Order.class, id);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
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
