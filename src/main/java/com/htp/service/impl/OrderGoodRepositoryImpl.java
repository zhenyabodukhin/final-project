package com.htp.service.impl;

import com.htp.domain.OrderGood;
import com.htp.repository.OrderGoodRepository;
import com.htp.service.OrderGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("OrderGoodRepositoryImpl")
public class OrderGoodRepositoryImpl implements OrderGoodService {

    @Autowired
    OrderGoodRepository orderGoodRepository;

    @Override
    public List<OrderGood> findAll() {
        return orderGoodRepository.findAll();
    }

    @Override
    public OrderGood save(OrderGood orderGood) {
        return orderGoodRepository.save(orderGood);
    }

    @Override
    public OrderGood update(OrderGood orderGood) {
        return orderGoodRepository.saveAndFlush(orderGood);
    }

    @Override
    public void delete(Long id) {
        orderGoodRepository.deleteById(id);
    }

    @Override
    public OrderGood findById(Long id) {
        return orderGoodRepository.getOne(id);
    }
}

