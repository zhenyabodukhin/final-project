package com.htp.service.impl;

import com.htp.domain.OrderGood;
import com.htp.repository.OrderGoodRepository;
import com.htp.service.OrderGoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderGoodServiceImpl implements OrderGoodService {

    private final OrderGoodRepository orderGoodRepository;

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
        return orderGoodRepository.findById(id).get();
    }

    @Override
    public List<OrderGood> findByOrderId(Long id) {
        return orderGoodRepository.findByOrderId(id);
    }
}

