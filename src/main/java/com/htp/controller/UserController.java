package com.htp.controller;

import com.htp.domain.User;
import com.htp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/deleted")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getUsersDeleted(Boolean value) {
        return new ResponseEntity<>(userService.findIsDeleted(value), HttpStatus.OK);
    }
}
