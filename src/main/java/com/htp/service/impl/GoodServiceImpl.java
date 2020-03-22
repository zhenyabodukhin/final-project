package com.htp.service.impl;

import com.htp.domain.Good;
import com.htp.repository.GoodRepository;
import com.htp.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("GoodServiceImpl")
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodRepository goodRepository;


    @Override
    public List<Good> findAll() {
        return goodRepository.findAll();
    }

    @Transactional
    @Override
    public Good save(Good good) {
        return goodRepository.save(good);
    }

    @Transactional
    @Override
    public Good update(Good good) {
        return goodRepository.saveAndFlush(good);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        goodRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Good findById(Long id) {
        return goodRepository.getOne(id);
    }

    @Override
    public Good findGoodByName(String name) {return goodRepository.findGoodByName(name); }
}

