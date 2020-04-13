package com.htp.service.impl;

import com.htp.domain.OrderGood;
import com.htp.exception.EntityNotFoundException;
import com.htp.repository.OrderGoodRepository;
import com.htp.service.OrderGoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderGoodServiceImpl implements OrderGoodService {

    private final OrderGoodRepository orderGoodRepository;

    @Override
    public List<OrderGood> findAll() {
        return orderGoodRepository.findAll();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public OrderGood save(OrderGood orderGood) {
        return orderGoodRepository.save(orderGood);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public OrderGood update(OrderGood orderGood) {
        return orderGoodRepository.saveAndFlush(orderGood);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(Long id) {
        if (orderGoodRepository.findById(id).isPresent()) {
            orderGoodRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(OrderGood.class, id);
        }
    }

    @Override
    public OrderGood findById(Long id) {
        Optional<OrderGood> result = orderGoodRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(OrderGood.class, id);
        }
    }

    @Override
    public List<OrderGood> findByOrderId(Long id) {
        return orderGoodRepository.findByOrderId(id);
    }
}

