package com.htp.controller;

import com.htp.controller.request.BucketPutRequest;
import com.htp.domain.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/bucket")
@RequiredArgsConstructor
public class BucketController {

    private final Bucket bucket;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> putIntoBucket(@RequestBody @Valid BucketPutRequest request) {
        bucket.putIntoBucket(request.getGoodId(), request.getCount());
        return new ResponseEntity<>(bucket.getSize(), HttpStatus.OK);
    }

    @DeleteMapping
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteAllItems() {
        bucket.clearBucket();
        return new ResponseEntity<>(bucket.getSize(), HttpStatus.OK);
    }

    @DeleteMapping("/id")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteItem(String goodId) {
        bucket.deleteItem(goodId);
        return new ResponseEntity<>(bucket.getSize(), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public void showBucketItems() {
        bucket.showItemsInBucket();
    }

    @GetMapping("/size")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> bucketSize() {
        return new ResponseEntity<>(bucket.getSize(), HttpStatus.OK);
    }

}
