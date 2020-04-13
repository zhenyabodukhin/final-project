package com.htp.controller;

import com.htp.controller.request.PriceCreateRequest;
import com.htp.domain.Price;
import com.htp.service.impl.PriceServiceImpl;
import io.swagger.annotations.*;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get all prices from server")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Price>> getPrices() {
        return new ResponseEntity<>(priceServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Create price")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful created"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Price> createPrice(@RequestBody @Valid PriceCreateRequest request) {
        Price price = new Price();

        price.setPrice(request.getPrice());

        return new ResponseEntity<>(priceServiceImpl.save(price), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Update price")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful updated"),
            @ApiResponse(code = 400, message = "Invalid price ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Price was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Price> updatePrice(@PathVariable("id") Long priceId,
                                             @RequestBody @Valid PriceCreateRequest request) {
        Price price = priceServiceImpl.findById(priceId);

        price.setPrice(request.getPrice());

        return new ResponseEntity<>(priceServiceImpl.update(price), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Delete price")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful deleted"),
            @ApiResponse(code = 400, message = "Invalid price ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Price was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deletePrice(@PathVariable("id") Long priceId) {
        priceServiceImpl.delete(priceId);
        return new ResponseEntity<>(priceId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get price by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid price ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Price was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Price> getPriceById(@PathVariable("id") Long priceId) {
        return new ResponseEntity<>(priceServiceImpl.findById(priceId), HttpStatus.OK);
    }
}
