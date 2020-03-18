package com.htp.service.impl;

import com.htp.domain.Adress;
import com.htp.repository.AdressRepository;
import com.htp.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("AdressServiceImpl")
public class AdressServiceImpl implements AdressService {

    @Autowired
    private AdressRepository adressRepository;


    @Override
    public List<Adress> findAll() {
        return adressRepository.findAll();
    }

    @Transactional
    @Override
    public Adress save(Adress adress) {
       return adressRepository.save(adress);
    }

    @Transactional
    @Override
    public Adress update(Adress adress) {
        return adressRepository.saveAndFlush(adress);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        adressRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Adress findById(Long id) {
        return adressRepository.getOne(id);
    }

    @Override
    public List<Adress> findContainsValue(String value) {
        return adressRepository.findContainsValue(value);
    }
}
