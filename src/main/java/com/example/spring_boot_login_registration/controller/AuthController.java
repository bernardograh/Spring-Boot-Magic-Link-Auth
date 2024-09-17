package com.example.spring_boot_login_registration.controller;

import com.example.spring_boot_login_registration.config.JwtTokenProvider;
import com.example.spring_boot_login_registration.entity.Token;
import com.example.spring_boot_login_registration.payload.JWTAuthResponse;
import com.example.spring_boot_login_registration.payload.LoginPayload;
import com.example.spring_boot_login_registration.payload.SignUpPayload;
import com.example.spring_boot_login_registration.repository.TokenRepository;
import com.example.spring_boot_login_registration.service.AuthService;
import com.example.spring_boot_login_registration.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;
    private TokenRepository tokenRepository;
    private EmailService emailService;
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SignUpPayload signUpPayload){
        return authService.register(signUpPayload);
    }

    @PostMapping("/login")
    public /*ResponseEntity<JWTAuthResponse>*/String login(@RequestBody LoginPayload loginPayload){
        String tokenId = authService.login(loginPayload);
        Token tokenEntity = new Token();
        String token = tokenRepository.findById(tokenId).get().getToken();

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        emailService.sendTokenMail(loginPayload, tokenId);

        return "Check email to confirm login!";
//        return ResponseEntity.ok(jwtAuthResponse);
    }

    @GetMapping("/verify")
    public /*ResponseEntity<JWTAuthResponse>*/String authenticate(@RequestParam(name = "token", defaultValue = "a.a.a") String tokenId){
        String token = tokenRepository.findById(tokenId).get().getToken();
        System.out.println(token);
        if (jwtTokenProvider.validateToken(token)) {
            return "User Authenticated: " + jwtTokenProvider.getUsername(token);
        } else {
            return "User unauthorized";
        }
    }

}