package com.htp.controller;

import com.htp.controller.request.RoleCreateRequest;
import com.htp.domain.Role;
import com.htp.service.impl.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(roleServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Role> createRole(@RequestBody @Valid RoleCreateRequest request) {
        Role role = new Role();

        role.setUserId(request.getUserId());
        role.setRole(request.getRole());

        return new ResponseEntity<>(roleServiceImpl.save(role), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Role> updateRole(@PathVariable("id") Long roleId,
                                           @RequestBody @Valid RoleCreateRequest request) {
        Role role = roleServiceImpl.findById(roleId);

        role.setUserId(request.getUserId());
        role.setRole(request.getRole());

        return new ResponseEntity<>(roleServiceImpl.update(role), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteRole(@PathVariable("id") Long roleId) {
        roleServiceImpl.delete(roleId);
        return new ResponseEntity<>(roleId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long roleId) {
        return new ResponseEntity<>(roleServiceImpl.findById(roleId), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Role>> getRoleByUserId(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(roleServiceImpl.findByUserId(userId), HttpStatus.OK);
    }
}
