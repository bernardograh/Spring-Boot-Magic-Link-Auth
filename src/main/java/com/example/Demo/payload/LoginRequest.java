package com.example.Demo.payload;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    private String username;

    public String getUsername() {
        return username;
    }

    public void setEmail(String username) {
        this.username = username;
    }
}
