package com.example.spring_boot_login_registration.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LoginPayload {
    private String usernameOrEmail;
    private String password = "D3f4ultP4$$w0rd";
}
