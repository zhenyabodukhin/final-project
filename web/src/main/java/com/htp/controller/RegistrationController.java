package com.htp.controller;

import com.htp.request.UserCreateByUserRequest;
import com.htp.domain.Role;
import com.htp.domain.User;
import com.htp.service.impl.RoleServiceImpl;
import com.htp.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserServiceImpl userServiceImpl;

    private final RoleServiceImpl roleService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody @Valid UserCreateByUserRequest request) {
        User user = new User();

        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreated(new Timestamp(new Date().getTime()));
        user.setChanged(new Timestamp(new Date().getTime()));
        user.setUserEmail(request.getEmail());
        user.setDeleted(false);

        User createdUser = userServiceImpl.save(user);

        Role role = new Role();

        role.setUserId(createdUser.getId());
        role.setRole("ROLE_USER");

        roleService.save(role);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
