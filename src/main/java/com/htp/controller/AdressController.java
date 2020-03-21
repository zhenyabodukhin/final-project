package com.htp.controller;

import com.htp.domain.Adress;
import com.htp.service.AdressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/adresses")
@RequiredArgsConstructor
public class AdressController {

    private final AdressService adressService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Adress>> getAdresses() {
        return new ResponseEntity<>(adressService.findAll(), HttpStatus.OK);
    }
}
