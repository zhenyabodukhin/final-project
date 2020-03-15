package com.htp.service.impl;

import com.htp.domain.DoughType;
import com.htp.repository.DoughTypeRepository;
import com.htp.service.DoughTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("DoughTypeRepositoryImpl")
public class DoughTypeRepositoryImpl implements DoughTypeService {

    @Autowired
    private DoughTypeRepository doughTypeRepository;

    @Override
    public List<DoughType> findAll() {
        return doughTypeRepository.findAll();
    }

    @Transactional
    @Override
    public DoughType save(DoughType doughType) {
        return doughTypeRepository.save(doughType);
    }

    @Transactional
    @Override
    public DoughType update(DoughType doughType) {
        return doughTypeRepository.saveAndFlush(doughType);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        doughTypeRepository.deleteById(id);
    }

    @Override
    public DoughType findById(Long id) {
        return doughTypeRepository.getOne(id);
    }
}