package com.htp.service.impl;

import com.htp.domain.Price;
import com.htp.repository.PriceRepository;
import com.htp.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

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

    @Override
    public Price findById(Long id) {
        return priceRepository.findById(id).get();
    }

    @Override
    public Price findPriceByValue(Double value) {
        return priceRepository.findPriceByValue(value);
    }
}
