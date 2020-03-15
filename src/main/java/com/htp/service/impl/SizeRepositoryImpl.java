package com.htp.service.impl;

import com.htp.domain.Size;
import com.htp.repository.SizeRepository;
import com.htp.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SizeRepositoryImpl")

public class SizeRepositoryImpl implements SizeService {

    @Autowired
    SizeRepository sizeRepository;

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size save(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public Size update(Size size) {
        return sizeRepository.saveAndFlush(size);
    }

    @Override
    public void delete(Long id) {
        sizeRepository.deleteById(id);
    }

    @Override
    public Size findById(Long id) {
        return sizeRepository.getOne(id);
    }
}

