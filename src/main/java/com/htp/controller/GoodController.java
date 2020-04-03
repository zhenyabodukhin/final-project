package com.htp.controller;

import com.htp.controller.request.BucketPutRequest;
import com.htp.controller.request.CustomGoodCreateRequest;
import com.htp.controller.request.GoodCreateRequest;
import com.htp.controller.request.GoodUpdateRequest;
import com.htp.domain.Bucket;
import com.htp.domain.Good;
import com.htp.service.impl.GoodServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/goods")
@RequiredArgsConstructor
public class GoodController {

    private final GoodServiceImpl goodServiceImpl;

    private final Bucket bucket;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Good>> getGoods() {
        return new ResponseEntity<>(goodServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Good> createGood(@RequestBody @Valid GoodCreateRequest request) {
        Good good = new Good();

        good.setGoodName(request.getGoodName());
        good.setPriceId(request.getPriceId());
        good.setGoodWeight(request.getWeight());
        good.setSizeId(request.getSizeId());
        good.setDoughId(request.getDoughId());
        good.setIngredients("");

        return new ResponseEntity<>(goodServiceImpl.save(good), HttpStatus.CREATED);
    }

    @PostMapping("/custom")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Good> createCustomGood(@RequestBody @Valid CustomGoodCreateRequest request) {
        return new ResponseEntity<>(goodServiceImpl.createCustomGood(request), HttpStatus.CREATED);
    }

    @Async
    @PostMapping("/bucket")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> bucket(@RequestBody @Valid BucketPutRequest request) {
        bucket.putIntoBucket(request.getGoodId(), request.getCount());
        return new ResponseEntity<>(bucket.getSize(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Good> updateGood(@PathVariable("id") Long goodId,
                                           @RequestBody @Valid GoodUpdateRequest request) {
        Good good = goodServiceImpl.findById(goodId);

        good.setGoodName(request.getGoodName());
        good.setPriceId(request.getPriceId());
        good.setGoodWeight(request.getWeight());
        good.setSizeId(request.getSizeId());
        good.setDoughId(request.getDoughId());
        good.setIngredients(request.getIngredients());

        return new ResponseEntity<>(goodServiceImpl.update(good), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteGood(@PathVariable("id") Long goodId) {
        goodServiceImpl.delete(goodId);
        return new ResponseEntity<>(goodId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Good> getGoodById(@PathVariable("id") Long goodId) {
        return new ResponseEntity<>(goodServiceImpl.findById(goodId), HttpStatus.OK);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Good> getGoodByName(String name) {
        return new ResponseEntity<>(goodServiceImpl.findGoodByName(name), HttpStatus.OK);
    }
}
