package com.htp.service.impl;

import com.htp.domain.OrderGood;
import com.htp.repository.OrderGoodRepository;
import com.htp.service.OrderGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("OrderGoodServiceImpl")
public class OrderGoodServiceImpl implements OrderGoodService {

    @Autowired
    OrderGoodRepository orderGoodRepository;

    @Override
    public List<OrderGood> findAll() {
        return orderGoodRepository.findAll();
    }

    @Transactional
    @Override
    public OrderGood save(OrderGood orderGood) {
        return orderGoodRepository.save(orderGood);
    }

    @Transactional
    @Override
    public OrderGood update(OrderGood orderGood) {
        return orderGoodRepository.saveAndFlush(orderGood);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        orderGoodRepository.deleteById(id);
    }

    @Override
    public OrderGood findById(Long id) {
        return orderGoodRepository.getOne(id);
    }
}

