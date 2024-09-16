package com.example.Demo.controller;

import com.example.Demo.entity.User;
import com.example.Demo.payload.LoginRequest;
import com.example.Demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        authService.register(user);
        return "Sign Up Successful";
    }

    @PostMapping("/login")
//    public String login(Authentication authentication) {
//        authService.login(authentication);
//        return "Confirmation Email Sent";
//    }
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        authService.login(loginRequest);
        return ResponseEntity.ok("Login successful");
    }

    @GetMapping("/authenticated")
    public String login(@RequestParam(name = "token", defaultValue = "") String tokenKey) {
        return "Authenticated: " + authService.authenticate(tokenKey);
    }
}
