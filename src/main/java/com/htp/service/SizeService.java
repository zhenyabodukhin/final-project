package com.htp.service;

import com.htp.domain.Size;

import java.util.List;

public interface SizeService {

    List<Size> findAll();

    Size save (Size size);

    Size update (Size size);

    void delete (Long id);

    Size findById(Long id);
}
