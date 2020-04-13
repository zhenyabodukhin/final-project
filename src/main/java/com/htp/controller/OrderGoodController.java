package com.htp.controller;

import com.htp.controller.request.OrderGoodCreateRequest;
import com.htp.domain.OrderGood;
import com.htp.service.impl.OrderGoodServiceImpl;
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
@RequestMapping(value = "/rest/orderGoods")
@RequiredArgsConstructor
public class OrderGoodController {

    private final OrderGoodServiceImpl orderGoodServiceImpl;

    @GetMapping("/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get all order goods from server")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OrderGood>> getOrderGoods() {
        return new ResponseEntity<>(orderGoodServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Create order good")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful created"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderGood> createOrderGood(@RequestBody @Valid OrderGoodCreateRequest request) {
        OrderGood orderGood = new OrderGood();

        orderGood.setOrderId(request.getOrderId());
        orderGood.setGoodId(request.getGoodId());
        orderGood.setGoodCount(request.getCount());

        return new ResponseEntity<>(orderGoodServiceImpl.save(orderGood), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Update order good")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful updated"),
            @ApiResponse(code = 400, message = "Invalid order good ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Order good was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Delete order good")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful deleted"),
            @ApiResponse(code = 400, message = "Invalid order good ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Order good was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteOrderGood(@PathVariable("id") Long orderGoodId) {
        orderGoodServiceImpl.delete(orderGoodId);
        return new ResponseEntity<>(orderGoodId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get order good by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid order good ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Order good was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderGood> getOrderGoodById(@PathVariable("id") Long orderGoodId) {
        return new ResponseEntity<>(orderGoodServiceImpl.findById(orderGoodId), HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get order good by order ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid order ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Order was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OrderGood>> getOrderGoodByOrderId(@PathVariable("id") Long orderId) {
        return new ResponseEntity<>(orderGoodServiceImpl.findByOrderId(orderId), HttpStatus.OK);
    }
}
