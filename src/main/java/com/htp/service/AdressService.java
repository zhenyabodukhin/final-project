package com.htp.service;

import com.htp.domain.Adress;
import com.htp.domain.DoughType;

import java.util.List;

public interface AdressService {

    List<Adress> findAll();

    Adress save (Adress adress);

    Adress update (Adress adress);

    void delete (Long id);

    Adress findById(Long id);


}
