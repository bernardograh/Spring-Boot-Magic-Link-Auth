package com.example.spring_boot_login_registration.payload;

import lombok.Data;

@Data
public class SignUpPayload {
    private String name;
    private String username;
    private String email;
    private String password;
}
