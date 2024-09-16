//package com.example.Demo.service;
//
//import com.example.Demo.entity.Token;
//import com.example.Demo.entity.User;
//import com.example.Demo.payload.LoginRequest;
//import com.example.Demo.repository.TokenRepository;
//import com.example.Demo.repository.UserRepository;
//import org.apache.catalina.Authenticator;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class AuthService {
//
//    private final TokenRepository tokenRepository;
//    private final TokenService tokenService;
//    private final EmailService emailService;
//    private final UserRepository userRepository;
//    private AuthenticationManager authenticationManager;
//
//    public AuthService(TokenRepository tokenRepository, TokenService tokenService, EmailService emailService, UserRepository userRepository) {
//        this.tokenRepository = tokenRepository;
//        this.tokenService = tokenService;
//        this.emailService = emailService;
//        this.userRepository = userRepository;
//    }
//
//    public void register(User user) {
//        userRepository.save(user);
//    }
//
//    public void login(Authentication authentication) {
//        Token tokenEntity = new Token();
//        tokenEntity.setToken(tokenService.generateToken(authentication));
//        String tokenKey = tokenEntity.getTokenKey();
//        tokenRepository.save(tokenEntity);
//
//        String recipient = authentication.getName()+"@gmail.com";
//
//        emailService.sendTokenMail(recipient, tokenKey);
//
//        System.out.println("TokenKey: " + tokenEntity.getTokenKey());
//        System.out.println("Token: " + tokenEntity.getToken());
//        System.out.println("TokenLength: " + tokenEntity.getToken().length());
//    }
//
//    public String authenticate(String tokenKey) {
//        Optional<Token> token = tokenRepository.findById(tokenKey);
//        if (token.isEmpty()) {
//            throw new IllegalStateException("No Token Exists");
//        }
//        return tokenService.getUsernameFromToken(token.get().getToken());
//
//    }
//}


package com.example.Demo.service;

import com.example.Demo.entity.Token;
import com.example.Demo.entity.User;
import com.example.Demo.payload.LoginRequest;
import com.example.Demo.repository.TokenRepository;
import com.example.Demo.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

@Service
public class AuthService {

    private final TokenRepository tokenRepository;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthService(TokenRepository tokenRepository, TokenService tokenService, EmailService emailService, UserRepository userRepository, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.tokenRepository = tokenRepository;
        this.tokenService = tokenService;
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    public void register(User user) {
        userRepository.save(user);
    }

    public void login(LoginRequest loginRequest) {
        // Create an Authentication object
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), null)
        );

        // Set the authentication in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate token and save it
        Token tokenEntity = new Token();
        tokenEntity.setToken(tokenService.generateToken(authentication));
        String tokenKey = tokenEntity.getTokenKey();
        tokenRepository.save(tokenEntity);

        // Send token via email
        String recipient = loginRequest.getUsername() + "@gmail.com";
        emailService.sendTokenMail(recipient, tokenKey);

        System.out.println("TokenKey: " + tokenEntity.getTokenKey());
        System.out.println("Token: " + tokenEntity.getToken());
        System.out.println("TokenLength: " + tokenEntity.getToken().length());
    }

    public String authenticate(String tokenKey) {
        Optional<Token> token = tokenRepository.findById(tokenKey);
        if (token.isEmpty()) {
            throw new IllegalStateException("No Token Exists");
        }
        return tokenService.getUsernameFromToken(token.get().getToken());
    }
}

