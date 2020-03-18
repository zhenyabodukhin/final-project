package com.htp.service;

import com.htp.domain.DoughType;
import com.htp.domain.Price;

import java.util.List;

public interface PriceService {

    List<Price> findAll();

    Price save (Price price);

    Price update (Price price);

    void delete (Long id);

    Price findById(Long id);
}
