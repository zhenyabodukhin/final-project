package com.htp.service.impl;

import com.htp.repository.AdressRepository;
import com.htp.domain.Adress;
import com.htp.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository("AdressTest")
public class AdressServiceImpl implements AdressService {

    @Autowired
    private AdressRepository adressRepository;


    @Override
    public List<Adress> findAll() {

        List<Adress> list = adressRepository.findAll();
        return list;
    }

    @Transactional
    @Override
    public Adress addAdress(Adress adress) {

       Adress savedAdress = adressRepository.saveAndFlush(adress) ;
       return savedAdress;
    }

    @Transactional
    @Override
    public Adress update(Adress adress) {
        return null;
    }

    @Transactional
    @Override
    public void delete(Long id) {

    }

    @Override
    public Adress findById(Long id) {
        return null;
    }
}
