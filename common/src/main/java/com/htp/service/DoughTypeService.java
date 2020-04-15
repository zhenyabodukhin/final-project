package com.htp.service;

import com.htp.domain.DoughType;

import java.util.List;

public interface DoughTypeService {

    List<DoughType> findAll();

    DoughType save (DoughType doughType);

    DoughType update (DoughType doughType);

    void delete (Long id);

    DoughType findById(Long id);
}
