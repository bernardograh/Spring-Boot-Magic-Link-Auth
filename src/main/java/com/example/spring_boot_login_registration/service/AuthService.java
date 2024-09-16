package com.example.spring_boot_login_registration.service;

import com.example.spring_boot_login_registration.payload.LoginPayload;

public interface AuthService {
    String login(LoginPayload loginPayload);
}