package com.medinear.medinear.controller;

import com.medinear.medinear.dto.LoginRequestDto;
import com.medinear.medinear.dto.LoginResponseDto;
import com.medinear.medinear.dto.RegisterRequestDto;
import com.medinear.medinear.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> register(
            @Valid @RequestBody RegisterRequestDto request) {

        return ResponseEntity.ok(
                authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto request) {

        return ResponseEntity.ok(
                authenticationService.login(request));
    }
}