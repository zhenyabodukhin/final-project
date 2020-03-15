package com.htp.service;

import com.htp.domain.Good;

import java.util.List;

public interface GoodService {

    List<Good> findAll();

    Good save (Good good);

    Good update (Good good);

    void delete (Long id);

    Good findById(Long id);
}
