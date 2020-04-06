package com.htp.service.impl;

import com.htp.domain.Size;
import com.htp.repository.SizeRepository;
import com.htp.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Transactional
    @Override
    public Size save(Size size) {
        return sizeRepository.save(size);
    }

    @Transactional
    @Override
    public Size update(Size size) {
        return sizeRepository.saveAndFlush(size);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        sizeRepository.deleteById(id);
    }

    @Override
    public Size findById(Long id) {
        return sizeRepository.findById(id).get();
    }
}

