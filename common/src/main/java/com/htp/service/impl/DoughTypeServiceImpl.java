package com.htp.service.impl;

import com.htp.domain.DoughType;
import com.htp.exception.EntityNotFoundException;
import com.htp.repository.DoughTypeRepository;
import com.htp.service.DoughTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoughTypeServiceImpl implements DoughTypeService {

    private final DoughTypeRepository doughTypeRepository;

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
        if (doughTypeRepository.findById(id).isPresent()) {
            doughTypeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(DoughType.class, id);
        }
    }

    @Override
    public DoughType findById(Long id) {
        Optional<DoughType> result = doughTypeRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(DoughType.class, id);
        }
    }
}