package com.htp.service;

import com.htp.domain.Adress;

import java.util.List;
import java.util.Optional;

public interface AdressService {

    List<Adress> findAll();

    Adress save (Adress adress);

    Adress update (Adress adress);

    void delete (Long id);

    Adress findById(Long id);

    List<Adress> findContainsValue (String value);
}
