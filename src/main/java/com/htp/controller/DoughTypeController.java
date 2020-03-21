package com.htp.controller;

import com.htp.domain.DoughType;
import com.htp.service.DoughTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/doughTypes")
@RequiredArgsConstructor
public class DoughTypeController {

    private final DoughTypeService doughTypeService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DoughType>> getDoughTypes() {
        return new ResponseEntity<>(doughTypeService.findAll(), HttpStatus.OK);
    }
}
