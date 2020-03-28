package com.htp.controller;

import com.htp.controller.request.OrderGoodCreateRequest;
import com.htp.domain.OrderGood;
import com.htp.service.impl.OrderGoodServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/orderGoods")
@RequiredArgsConstructor
public class OrderGoodController {

    private final OrderGoodServiceImpl orderGoodServiceImpl;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OrderGood>> getOrderGoods() {
        return new ResponseEntity<>(orderGoodServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderGood> createOrderGood(@RequestBody @Valid OrderGoodCreateRequest request) {
        OrderGood orderGood = new OrderGood();

        orderGood.setOrderId(request.getOrderId());
        orderGood.setGoodId(request.getGoodId());
        orderGood.setGoodCount(request.getCount());

        return new ResponseEntity<>(orderGoodServiceImpl.save(orderGood), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderGood> updateOrderGood(@PathVariable("id") Long orderGoodId,
                                                     @RequestBody @Valid OrderGoodCreateRequest request) {
        OrderGood orderGood = orderGoodServiceImpl.findById(orderGoodId);

        orderGood.setOrderId(request.getOrderId());
        orderGood.setGoodId(request.getGoodId());
        orderGood.setGoodCount(request.getCount());

        return new ResponseEntity<>(orderGoodServiceImpl.update(orderGood), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteOrderGood(@PathVariable("id") Long orderGoodId) {
        orderGoodServiceImpl.delete(orderGoodId);
        return new ResponseEntity<>(orderGoodId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderGood> getOrderGoodById(@PathVariable("id") Long orderGoodId) {
        return new ResponseEntity<>(orderGoodServiceImpl.findById(orderGoodId), HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OrderGood>> getOrderGoodByOrderId(@PathVariable("id") Long orderId) {
        return new ResponseEntity<>(orderGoodServiceImpl.findByOrderId(orderId), HttpStatus.OK);
    }
}
