package com.htp.controller;

import com.htp.controller.request.BucketPutRequest;
import com.htp.domain.Bucket;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/bucket")
@RequiredArgsConstructor
public class BucketController {

    private final Bucket bucket;

    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<Long, Integer>> putIntoBucket(@RequestBody @Valid BucketPutRequest request) {
        return new ResponseEntity<>(bucket.putIntoBucket(request.getGoodId(), request.getCount()), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteAllItems() {
        bucket.clearBucket();
        return new ResponseEntity<>(bucket.getSize(), HttpStatus.OK);
    }

    @DeleteMapping("/id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<Long, Integer>> deleteItem(Long goodId) {
        return new ResponseEntity<>(bucket.deleteItem(goodId), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<Long, Integer>> showBucketItems() {
        return new ResponseEntity<>(bucket.showItemsInBucket(), HttpStatus.OK);
    }

    @GetMapping("/size")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> bucketSize() {
        return new ResponseEntity<>(bucket.getSize(), HttpStatus.OK);
    }

}
