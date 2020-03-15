package com.htp.service;

import com.htp.domain.OrderGood;

import java.util.List;

public interface OrderGoodService {

    List<OrderGood> findAll();

    OrderGood save (OrderGood orderGood);

    OrderGood update (OrderGood orderGood);

    void delete (Long id);

    OrderGood findById(Long id);
}
