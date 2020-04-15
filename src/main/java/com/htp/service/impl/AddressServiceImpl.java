package com.htp.service.impl;

import com.htp.domain.Address;
import com.htp.exception.EntityNotFoundException;
import com.htp.repository.AddressRepository;
import com.htp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        if (addressRepository.findById(id).isPresent()) {
            addressRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(Address.class, id);
        }
    }

    @Override
    public Address findById(Long id) {
        Optional<Address> result = addressRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(Address.class, id);
        }
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
