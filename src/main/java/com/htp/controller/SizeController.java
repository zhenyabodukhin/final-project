package com.htp.controller;

import com.htp.controller.request.SizeCreateRequest;
import com.htp.domain.Size;
import com.htp.service.impl.SizeServiceImpl;
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
@RequestMapping(value = "/rest/sizes")
@RequiredArgsConstructor
public class SizeController {

    private final SizeServiceImpl sizeServiceImpl;

    @GetMapping("/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get all sizes from server")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Size>> getSizes() {
        return new ResponseEntity<>(sizeServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Create size")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful created"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Size> createSize(@RequestBody @Valid SizeCreateRequest request) {
        Size size = new Size();

        size.setSizeCount(request.getSize());
        size.setPriceId(request.getPriceId());

        return new ResponseEntity<>(sizeServiceImpl.save(size), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Update size")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful updated"),
            @ApiResponse(code = 400, message = "Invalid size ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Size was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Size> updateSize(@PathVariable("id") Long sizeId,
                                           @RequestBody @Valid SizeCreateRequest request) {
        Size size = sizeServiceImpl.findById(sizeId);

        size.setSizeCount(request.getSize());
        size.setPriceId(request.getPriceId());

        return new ResponseEntity<>(sizeServiceImpl.update(size), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Delete size")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful deleted"),
            @ApiResponse(code = 400, message = "Invalid size ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Size was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteSize(@PathVariable("id") Long sizeId) {
        sizeServiceImpl.delete(sizeId);
        return new ResponseEntity<>(sizeId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get size by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid size ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Size was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Size> getSizeById(@PathVariable("id") Long sizeId) {
        return new ResponseEntity<>(sizeServiceImpl.findById(sizeId), HttpStatus.OK);
    }
}
