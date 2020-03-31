package com.htp.controller;

import com.htp.controller.request.UserCreateRequest;
import com.htp.domain.User;
import com.htp.service.impl.UserServiceImpl;
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
@RequestMapping(value = "/rest/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody @Valid UserCreateRequest request) {
        User user = new User();

        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setCreated(new Timestamp(new Date().getTime()));
        user.setChanged(new Timestamp(new Date().getTime()));
        user.setDeleted(false);

        return new ResponseEntity<>(userServiceImpl.save(user), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody @Valid UserCreateRequest request) {
        User user = userServiceImpl.findById(userId);

        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setChanged(new Timestamp(new Date().getTime()));

        return new ResponseEntity<>(userServiceImpl.update(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteUser(@PathVariable("id") Long userId) {
        User user = userServiceImpl.findById(userId);

        userServiceImpl.delete(userId);
        user.setChanged(new Timestamp(new Date().getTime()));

        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(userServiceImpl.findById(userId), HttpStatus.OK);
    }

    @GetMapping("/search/name")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getUserByName(String name) {
        return new ResponseEntity<>(userServiceImpl.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/search/value")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getUsersByValue(String value) {
        return new ResponseEntity<>(userServiceImpl.findContainsValue(value), HttpStatus.OK);
    }

    @GetMapping("/deleted")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getUsersDeleted(Boolean value) {
        return new ResponseEntity<>(userServiceImpl.findIsDeleted(value), HttpStatus.OK);
    }
}
