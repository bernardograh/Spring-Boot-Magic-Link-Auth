//package com.example.Demo.security;
//
//import com.example.Demo.payload.LoginRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final ObjectMapper objectMapper;
//    private final AuthenticationManager authenticationManager;
//
//    public CustomAuthenticationFilter(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//            throws AuthenticationException {
//
//        try {
//            // Read the credentials from the request
//            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
//
//            // Create an Authentication token with the username and password
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                    loginRequest.getUsername(),
//                    null
//            );
//
//            // Return the authentication token
//            return authenticationManager.authenticate(authenticationToken);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
