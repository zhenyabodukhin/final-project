package com.htp.controller;

import com.htp.controller.request.AddressCreateByAdminRequest;
import com.htp.controller.request.AddressCreateByUserRequest;
import com.htp.domain.Address;
import com.htp.service.impl.AddressServiceImpl;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @ApiOperation(value = "Get all addresses from server")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting addresses"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Address>> getAddresses() {
        return new ResponseEntity<>(addressServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping("/admin")
    @PreAuthorize(value = "hasRole('ADMIN')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Create address by admin")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful created"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Address> createAddressByAdmin(@RequestBody @Valid AddressCreateByAdminRequest request){
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
    @PreAuthorize(value = "hasRole('ADMIN')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Update address by admin")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful updated"),
            @ApiResponse(code = 400, message = "Invalid Address ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Address was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Address> updateAddressByAdmin(@PathVariable("id") Long addressId,
                                                @RequestBody @Valid AddressCreateByAdminRequest request){
        Address address = addressServiceImpl.findById(addressId);

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
    @ApiOperation(value = "Create address by user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful updated"),
            @ApiResponse(code = 400, message = "Invalid Address ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Address was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Address> createAddress(@RequestBody @Valid AddressCreateByUserRequest request){
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
    @ApiOperation(value = "Update address by user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful updated"),
            @ApiResponse(code = 400, message = "Invalid Address ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Address was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Address> updateAddress(@PathVariable("id") Long addressId,
                                                 @RequestBody @Valid AddressCreateByUserRequest request){
        Address address = addressServiceImpl.findById(addressId);

        address.setStreet(request.getStreet());
        address.setHouseNumber(request.getHouseNumber());
        address.setPorchNumber(request.getPorchNumber());
        address.setFloorNumber(request.getFloorNumber());
        address.setFlatNumber(request.getFlatNumber());
        address.setIsPizzaAddress(false);

        return new ResponseEntity<>(addressServiceImpl.update(address), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Delete address")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful deleted"),
            @ApiResponse(code = 400, message = "Invalid Address ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Address was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteAddress(@PathVariable("id") Long addressId){
        addressServiceImpl.delete(addressId);
        return new ResponseEntity<>(addressId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get address by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid Address ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Address was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Address> getAddressById(@PathVariable("id") Long addressId) {
        return new ResponseEntity<>(addressServiceImpl.findById(addressId), HttpStatus.OK);
    }

    @GetMapping("/search")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Search addresses by value")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Address was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })

    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Address>> getAddressesByValue(String value) {
        return new ResponseEntity<>(addressServiceImpl.findContainsValue(value), HttpStatus.OK);
    }

    @GetMapping("/pizza")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get pizza addresses")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Address was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Address>> getPizzaAddresses(Boolean value) {
        return new ResponseEntity<>(addressServiceImpl.findIsPizza(value), HttpStatus.OK);
    }
}
