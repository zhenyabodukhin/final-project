package com.htp.controller;

import com.htp.controller.request.PriceCreateRequest;
import com.htp.domain.Price;
import com.htp.service.impl.PriceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceServiceImpl priceServiceImpl;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Price>> getPrices() {
        return new ResponseEntity<>(priceServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Price> createPrice(@RequestBody @Valid PriceCreateRequest request) {
        Price price = new Price();

        price.setPrice(request.getPrice());

        return new ResponseEntity<>(priceServiceImpl.save(price), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Price> updatePrice(@PathVariable("id") Long priceId,
                                             @RequestBody @Valid PriceCreateRequest request) {
        Price price = priceServiceImpl.findById(priceId);

        price.setPrice(request.getPrice());

        return new ResponseEntity<>(priceServiceImpl.update(price), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deletePrice(@PathVariable("id") Long priceId) {
        priceServiceImpl.delete(priceId);
        return new ResponseEntity<>(priceId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Price> getPriceById(@PathVariable("id") Long priceId) {
        return new ResponseEntity<>(priceServiceImpl.findById(priceId), HttpStatus.OK);
    }
}
