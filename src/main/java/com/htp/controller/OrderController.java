package com.htp.controller;

import com.htp.controller.request.OrderCreateRequest;
import com.htp.domain.Order;
import com.htp.service.impl.OrderServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    @GetMapping("/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(orderServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderCreateRequest request) {
        Order order = new Order();

        order.setUserId(request.getUserId());
        order.setAddressId(request.getAddressId());
        order.setTime(new Timestamp(new Date().getTime()));
        order.setPhoneNumber(request.getPhoneNumber());
        order.setDone(false);

        return new ResponseEntity<>(orderServiceImpl.save(order), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long orderId,
                                             @RequestBody @Valid OrderCreateRequest request) {
        Order order = orderServiceImpl.findById(orderId);

        order.setUserId(request.getUserId());
        order.setAddressId(request.getAddressId());
        order.setTime(new Timestamp(new Date().getTime()));
        order.setPhoneNumber(request.getPhoneNumber());
        order.setDone(request.getIsDone());

        return new ResponseEntity<>(orderServiceImpl.update(order), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteOrder(@PathVariable("id") Long orderId) {
        orderServiceImpl.delete(orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long orderId) {
        return new ResponseEntity<>(orderServiceImpl.findById(orderId), HttpStatus.OK);
    }

    @PutMapping(value = "setDone/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> setOrderDone(@PathVariable("id") Long orderId) {
        orderServiceImpl.setOrderDone(orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(orderServiceImpl.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/done")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Order>> getOrderIsDone(Boolean isDone) {
        return new ResponseEntity<>(orderServiceImpl.findIsDone(isDone), HttpStatus.OK);
    }

    @GetMapping("/address/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Order>> getOrderByAddressId(@PathVariable("id") Long addressId) {
        return new ResponseEntity<>(orderServiceImpl.findByAddressId(addressId), HttpStatus.OK);
    }

    @GetMapping("/phone")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Order>> getOrderByPhoneNumber(String phoneNumber) {
        return new ResponseEntity<>(orderServiceImpl.findByPhoneNumber(phoneNumber), HttpStatus.OK);
    }
}
