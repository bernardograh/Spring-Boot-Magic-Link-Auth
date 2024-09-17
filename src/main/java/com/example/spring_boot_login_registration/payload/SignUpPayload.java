package com.example.spring_boot_login_registration.payload;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class SignUpPayload {
    private String name;
    private String username;
    private String email;
    private String password = "D3f4ultP4$$w0rd";
}
