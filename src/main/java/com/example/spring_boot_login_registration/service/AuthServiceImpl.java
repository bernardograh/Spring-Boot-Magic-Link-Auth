package com.example.spring_boot_login_registration.service;

import com.example.spring_boot_login_registration.config.JwtTokenProvider;
import com.example.spring_boot_login_registration.entity.Role;
import com.example.spring_boot_login_registration.entity.Token;
import com.example.spring_boot_login_registration.entity.User;
import com.example.spring_boot_login_registration.payload.LoginPayload;
import com.example.spring_boot_login_registration.payload.SignUpPayload;
import com.example.spring_boot_login_registration.repository.RoleRepository;
import com.example.spring_boot_login_registration.repository.TokenRepository;
import com.example.spring_boot_login_registration.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;


    public AuthServiceImpl(
            JwtTokenProvider jwtTokenProvider,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, RoleRepository roleRepository, TokenRepository tokenRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String login(LoginPayload loginPayload) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginPayload.getUsernameOrEmail(), loginPayload.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenRepository.save(tokenEntity);
        return tokenRepository.findByToken(token).get().getId();
    }

    public ResponseEntity<String> register(SignUpPayload signUpPayload) {
        if (userRepository.existsByUsername(signUpPayload.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setEmail(signUpPayload.getEmail());
        user.setUsername(signUpPayload.getUsername());
        user.setName(signUpPayload.getName());
        user.setPassword(passwordEncoder.encode(signUpPayload.getPassword()));

        Role roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);

    }
}
