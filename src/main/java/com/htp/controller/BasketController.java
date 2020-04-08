package com.htp.controller;

import com.htp.controller.request.BucketPutRequest;
import com.htp.domain.Basket;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/basket")
@RequiredArgsConstructor
public class BasketController {

    private final Basket basket;

    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<Long, Integer>> putIntoBucket(@RequestBody @Valid BucketPutRequest request) {
        return new ResponseEntity<>(basket.putIntoBasket(request.getGoodId(), request.getCount()), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteAllItems() {
        basket.clearBasket();
        return new ResponseEntity<>(basket.getSize(), HttpStatus.OK);
    }

    @DeleteMapping("/id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<Long, Integer>> deleteItem(Long goodId) {
        return new ResponseEntity<>(basket.deleteItem(goodId), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<Long, Integer>> showBucketItems() {
        return new ResponseEntity<>(basket.showItemsInBasket(), HttpStatus.OK);
    }

    @GetMapping("/size")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> bucketSize() {
        return new ResponseEntity<>(basket.getSize(), HttpStatus.OK);
    }

}
