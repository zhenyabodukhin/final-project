package com.htp.service;

import com.htp.domain.Address;

import java.util.List;

public interface AddressService {

    List<Address> findAll();

    Address save (Address address);

    Address update (Address address);

    void delete (Long id);

    Address findById(Long id);

    List<Address> findContainsValue (String value);

    List<Address> findIsPizza(Boolean value);
}
