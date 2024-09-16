package com.example.spring_boot_login_registration.controller;

import com.example.spring_boot_login_registration.payload.JWTAuthResponse;
import com.example.spring_boot_login_registration.payload.LoginPayload;
import com.example.spring_boot_login_registration.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginPayload loginPayload){
        String token = authService.login(loginPayload);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }
}