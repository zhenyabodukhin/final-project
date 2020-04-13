package com.htp.service.impl;

import com.htp.domain.Price;
import com.htp.exception.EntityNotFoundException;
import com.htp.repository.PriceRepository;
import com.htp.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public List<Price> findAll() {
        return priceRepository.findAll();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Price save(Price price) {
        return priceRepository.save(price);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Price update(Price price) {
        return priceRepository.saveAndFlush(price);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(Long id) {
        if (priceRepository.findById(id).isPresent()) {
            priceRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(Price.class, id);
        }
    }

    @Override
    public Price findById(Long id) {
        Optional<Price> result = priceRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(Price.class, id);
        }
    }

    @Override
    public Price findPriceByValue(Double value) {
        return priceRepository.findPriceByValue(value);
    }
}
