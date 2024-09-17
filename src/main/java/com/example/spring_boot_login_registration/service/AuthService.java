package com.example.spring_boot_login_registration.service;

import com.example.spring_boot_login_registration.payload.LoginPayload;
import com.example.spring_boot_login_registration.payload.SignUpPayload;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    String login(LoginPayload loginPayload);
    ResponseEntity<String> register(SignUpPayload signUpPayload);
}