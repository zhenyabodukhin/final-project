package com.htp.controller;

import com.htp.controller.request.DoughTypeCreateRequest;
import com.htp.domain.DoughType;
import com.htp.service.DoughTypeService;
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

    private final DoughTypeService doughTypeService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DoughType>> getDoughTypes() {
        return new ResponseEntity<>(doughTypeService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DoughType> createDoughType(@RequestBody @Valid DoughTypeCreateRequest request) {
        DoughType doughType = new DoughType();

        doughType.setDoughType(request.getType());
        doughType.setPriceId(request.getPriceId());

        return new ResponseEntity<>(doughTypeService.save(doughType), HttpStatus.CREATED);
    }

    @PutMapping(value ="/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DoughType> updateDoughType(@PathVariable("id") Long doughTypeId,
                                                     @RequestBody @Valid DoughTypeCreateRequest request) {
        DoughType doughType = doughTypeService.findById(doughTypeId);

        doughType.setDoughType(request.getType());
        doughType.setPriceId(request.getPriceId());

        return new ResponseEntity<>(doughTypeService.update(doughType), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteDoughType(@PathVariable("id") Long doughTypeId){
        doughTypeService.delete(doughTypeId);
        return new ResponseEntity<>(doughTypeId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DoughType> getDoughTypeById(@PathVariable("id") Long doughTypeId) {
        return new ResponseEntity<>(doughTypeService.findById(doughTypeId), HttpStatus.OK);
    }
}
