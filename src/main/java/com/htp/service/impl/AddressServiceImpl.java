package com.htp.service.impl;

import com.htp.domain.Address;
import com.htp.repository.AddressRepository;
import com.htp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;


    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Transactional
    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Transactional
    @Override
    public Address update(Address address) {
        return addressRepository.saveAndFlush(address);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).get();
    }

    @Override
    public List<Address> findContainsValue(String value) {
        return addressRepository.findContainsValue(value);
    }

    @Override
    public List<Address> findIsPizza(Boolean value) {
        return addressRepository.findIsPizza(value);
    }
}
