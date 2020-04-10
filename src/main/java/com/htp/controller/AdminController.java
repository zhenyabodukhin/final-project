package com.htp.controller;

import com.htp.domain.Address;
import com.htp.service.impl.AddressServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AddressServiceImpl addressServiceImpl;

    @GetMapping("/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Address>> getAddresses() {
        return new ResponseEntity<>(addressServiceImpl.findAll(), HttpStatus.OK);
    }
}
