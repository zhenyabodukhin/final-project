package com.htp.controller;

import com.htp.domain.Good;
import com.htp.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/goods")
@RequiredArgsConstructor
public class GoodController {

    private final GoodService goodService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Good>> getGoods() {
        return new ResponseEntity<>(goodService.findAll(), HttpStatus.OK);
    }
}
