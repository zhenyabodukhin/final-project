package com.htp.controller;

import com.htp.request.UserCreateByAdminRequest;
import com.htp.request.UserUpdateRequest;
import com.htp.domain.User;
import com.htp.service.impl.UserServiceImpl;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    @PreAuthorize(value = "hasRole('ADMIN')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get all users from server")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize(value = "hasRole('ADMIN')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Create user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUserByAdmin(@RequestBody @Valid UserCreateByAdminRequest request) {
        User user = new User();

        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreated(new Timestamp(new Date().getTime()));
        user.setChanged(new Timestamp(new Date().getTime()));
        user.setUserEmail(request.getEmail());
        user.setDeleted(request.getIsDeleted());

        return new ResponseEntity<>(userServiceImpl.save(user), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Update user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful updated"),
            @ApiResponse(code = 400, message = "Invalid user ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody @Valid UserUpdateRequest request) {
        User user = userServiceImpl.findById(userId);

        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserEmail(request.getEmail());
        user.setChanged(new Timestamp(new Date().getTime()));

        return new ResponseEntity<>(userServiceImpl.update(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Delete user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful deleted"),
            @ApiResponse(code = 400, message = "Invalid user ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteUser(@PathVariable("id") Long userId) {
        User user = userServiceImpl.findById(userId);

        userServiceImpl.delete(userId);
        user.setChanged(new Timestamp(new Date().getTime()));

        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get user by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid user ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(userServiceImpl.findById(userId), HttpStatus.OK);
    }

    @GetMapping("/search/name")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get user by name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid user name supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getUserByName(String name) {
        return new ResponseEntity<>(userServiceImpl.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/search/value")
    @PreAuthorize(value = "hasRole('ADMIN')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get user by value")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid user value supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getUsersByValue(String value) {
        return new ResponseEntity<>(userServiceImpl.findContainsValue(value), HttpStatus.OK);
    }

    @GetMapping("/deleted")
    @PreAuthorize(value = "hasRole('ADMIN')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get deleted users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getUsersDeleted(Boolean value) {
        return new ResponseEntity<>(userServiceImpl.findIsDeleted(value), HttpStatus.OK);
    }
}
