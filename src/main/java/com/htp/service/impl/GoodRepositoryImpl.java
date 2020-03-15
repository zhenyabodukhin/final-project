package com.htp.service.impl;

import com.htp.domain.Good;
import com.htp.repository.GoodRepository;
import com.htp.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("GoodRepositoryImpl")
public class GoodRepositoryImpl implements GoodService {

    @Autowired
    GoodRepository goodRepository;


    @Override
    public List<Good> findAll() {
        return goodRepository.findAll();
    }

    @Override
    public Good save(Good good) {
        return goodRepository.save(good);
    }

    @Override
    public Good update(Good good) {
        return goodRepository.saveAndFlush(good);
    }

    @Override
    public void delete(Long id) {
        goodRepository.deleteById(id);
    }

    @Override
    public Good findById(Long id) {
        return goodRepository.getOne(id);
    }
}

