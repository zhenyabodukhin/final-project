package com.htp.controller;

import com.htp.domain.OrderGood;
import com.htp.service.OrderGoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/orderGoods")
@RequiredArgsConstructor
public class OrderGoodController {

    private final OrderGoodService orderGoodService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OrderGood>> getOrderGoods() {
        return new ResponseEntity<>(orderGoodService.findAll(), HttpStatus.OK);
    }
}
