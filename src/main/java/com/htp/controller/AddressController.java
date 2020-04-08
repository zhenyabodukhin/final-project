package com.htp.controller;

import com.htp.controller.request.AdressCreateRequest;
import com.htp.domain.Address;
import com.htp.service.impl.AddressServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressServiceImpl addressServiceImpl;

    @GetMapping("/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Address>> getAddresses() {
        return new ResponseEntity<>(addressServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping("/admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Address> createAddressByAdmin(@RequestBody @Valid AdressCreateRequest request){
        Address address = new Address();

        address.setStreet(request.getStreet());
        address.setHouseNumber(request.getHouseNumber());
        address.setPorchNumber(request.getPorchNumber());
        address.setFloorNumber(request.getFloorNumber());
        address.setFlatNumber(request.getFlatNumber());
        address.setIsPizzaAddress(request.getIsPizza());

        return new ResponseEntity<>(addressServiceImpl.save(address), HttpStatus.CREATED);
    }

    @PutMapping(value = "/admin/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Address> updateAddressByAdmin(@PathVariable("id") Long adressId,
                                                @RequestBody @Valid AdressCreateRequest request){
        Address address = addressServiceImpl.findById(adressId);

        address.setStreet(request.getStreet());
        address.setHouseNumber(request.getHouseNumber());
        address.setPorchNumber(request.getPorchNumber());
        address.setFloorNumber(request.getFloorNumber());
        address.setFlatNumber(request.getFlatNumber());
        address.setIsPizzaAddress(request.getIsPizza());

        return new ResponseEntity<>(addressServiceImpl.update(address), HttpStatus.OK);
    }

    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Address> createAddress(@RequestBody @Valid AdressCreateRequest request){
        Address address = new Address();

        address.setStreet(request.getStreet());
        address.setHouseNumber(request.getHouseNumber());
        address.setPorchNumber(request.getPorchNumber());
        address.setFloorNumber(request.getFloorNumber());
        address.setFlatNumber(request.getFlatNumber());
        address.setIsPizzaAddress(false);

        return new ResponseEntity<>(addressServiceImpl.save(address), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Address> updateAddress(@PathVariable("id") Long adressId,
                                                 @RequestBody @Valid AdressCreateRequest request){
        Address address = addressServiceImpl.findById(adressId);

        address.setStreet(request.getStreet());
        address.setHouseNumber(request.getHouseNumber());
        address.setPorchNumber(request.getPorchNumber());
        address.setFloorNumber(request.getFloorNumber());
        address.setFlatNumber(request.getFlatNumber());
        address.setIsPizzaAddress(false);

        return new ResponseEntity<>(addressServiceImpl.update(address), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteAddress(@PathVariable("id") Long adressId){
        addressServiceImpl.delete(adressId);
        return new ResponseEntity<>(adressId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Address> getAddressById(@PathVariable("id") Long addressId) {
        return new ResponseEntity<>(addressServiceImpl.findById(addressId), HttpStatus.OK);
    }

    @GetMapping("/search")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Address>> getAddressesByValue(String value) {
        return new ResponseEntity<>(addressServiceImpl.findContainsValue(value), HttpStatus.OK);
    }

    @GetMapping("/pizza")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Address>> getPizzaAddresses(Boolean value) {
        return new ResponseEntity<>(addressServiceImpl.findIsPizza(value), HttpStatus.OK);
    }

}
