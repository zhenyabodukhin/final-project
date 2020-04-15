package com.htp.controller;

import com.htp.controller.request.RoleCreateRequest;
import com.htp.domain.Role;
import com.htp.service.impl.RoleServiceImpl;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleServiceImpl roleServiceImpl;

    @GetMapping("/all")
    @PreAuthorize(value = "hasRole('ADMIN')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get all roles from sever")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(roleServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize(value = "hasRole('ADMIN')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Create role")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful created"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Role> createRole(@RequestBody @Valid RoleCreateRequest request) {
        Role role = new Role();

        role.setUserId(request.getUserId());
        role.setRole(request.getRole());

        return new ResponseEntity<>(roleServiceImpl.save(role), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Update role")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful updated"),
            @ApiResponse(code = 400, message = "Invalid role ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Role was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Role> updateRole(@PathVariable("id") Long roleId,
                                           @RequestBody @Valid RoleCreateRequest request) {
        Role role = roleServiceImpl.findById(roleId);

        role.setUserId(request.getUserId());
        role.setRole(request.getRole());

        return new ResponseEntity<>(roleServiceImpl.update(role), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Delete role")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful deleted"),
            @ApiResponse(code = 400, message = "Invalid role ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Role was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteRole(@PathVariable("id") Long roleId) {
        roleServiceImpl.delete(roleId);
        return new ResponseEntity<>(roleId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get role by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid role ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Role was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long roleId) {
        return new ResponseEntity<>(roleServiceImpl.findById(roleId), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiOperation(value = "Get role by user ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid user ID supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Role>> getRoleByUserId(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(roleServiceImpl.findByUserId(userId), HttpStatus.OK);
    }
}
