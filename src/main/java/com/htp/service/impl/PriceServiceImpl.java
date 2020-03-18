package com.htp.service.impl;

import com.htp.domain.Price;
import com.htp.repository.PriceRepository;
import com.htp.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("PriceServiceImpl")
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public List<Price> findAll() {
        return priceRepository.findAll();
    }

    @Transactional
    @Override
    public Price save(Price price) {
        return priceRepository.save(price);
    }

    @Transactional
    @Override
    public Price update(Price price) {
        return priceRepository.saveAndFlush(price);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        priceRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Price findById(Long id) {
        return priceRepository.getOne(id);
    }
}
