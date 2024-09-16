package com.example.spring_boot_login_registration.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginPayload {
    private String usernameOrEmail;
    private String password;
}
