package com.htp.security.controller;

import com.htp.security.model.AuthenticationRequest;
import com.htp.security.model.AuthenticationResponse;
import com.htp.security.util.TokenUtils;
import com.htp.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final TokenUtils tokenUtils;

    private final UserDetailsService userDetailsService;

    private final UserServiceImpl userService;

    @ApiOperation(value = "Login", notes = "Return Auth-Token with login")
    @PostMapping
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String authToken = tokenUtils.generateToken(userDetailsService.loadUserByUsername(request.getUserName()));

        return ResponseEntity.ok(AuthenticationResponse
                .builder()
                .login(request.getUserName())
                .userId(userService.findByName(request.getUserName()).getId())
                .authToken(authToken)
                .build()
        );
    }
}
