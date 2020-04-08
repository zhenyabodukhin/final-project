package com.htp.controller;

import com.htp.controller.request.DoughTypeCreateRequest;
import com.htp.domain.DoughType;
import com.htp.service.impl.DoughTypeServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/doughTypes")
@RequiredArgsConstructor
public class DoughTypeController {

    private final DoughTypeServiceImpl doughTypeServiceImpl;

    @GetMapping("/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DoughType>> getDoughTypes() {
        return new ResponseEntity<>(doughTypeServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DoughType> createDoughType(@RequestBody @Valid DoughTypeCreateRequest request) {
        DoughType doughType = new DoughType();

        doughType.setDoughType(request.getType());
        doughType.setPriceId(request.getPriceId());

        return new ResponseEntity<>(doughTypeServiceImpl.save(doughType), HttpStatus.CREATED);
    }

    @PutMapping(value ="/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DoughType> updateDoughType(@PathVariable("id") Long doughTypeId,
                                                     @RequestBody @Valid DoughTypeCreateRequest request) {
        DoughType doughType = doughTypeServiceImpl.findById(doughTypeId);

        doughType.setDoughType(request.getType());
        doughType.setPriceId(request.getPriceId());

        return new ResponseEntity<>(doughTypeServiceImpl.update(doughType), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteDoughType(@PathVariable("id") Long doughTypeId){
        doughTypeServiceImpl.delete(doughTypeId);
        return new ResponseEntity<>(doughTypeId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DoughType> getDoughTypeById(@PathVariable("id") Long doughTypeId) {
        return new ResponseEntity<>(doughTypeServiceImpl.findById(doughTypeId), HttpStatus.OK);
    }
}
