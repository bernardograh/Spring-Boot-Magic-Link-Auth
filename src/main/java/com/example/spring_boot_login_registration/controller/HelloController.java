package com.example.spring_boot_login_registration.controller;

import com.example.spring_boot_login_registration.config.JwtTokenProvider;
import com.example.spring_boot_login_registration.entity.User;
import com.example.spring_boot_login_registration.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
public class HelloController {

    private final UserRepository userRepository;
    private final JWTTest jwt;
    private final JwtTokenProvider jwtTokenProvider;

    public HelloController(UserRepository userRepository, JWTTest jwt, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwt = jwt;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void anyMethod(){
        HttpServletRequest req = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Collections.list(req.getHeaderNames()).forEach(System.out::println);
        System.out.println(req.getHeader("Authorization"));
        String bearerToken = req.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            System.out.println(bearerToken.substring(7));
        } else {
            System.out.println("No Bearer Token");
        }
    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        return("Hello, " + name);
    }

    @GetMapping("/hello")
    public void testJwt(@RequestParam(name = "token", defaultValue = "a.a.a") String token) {
        jwt.jwtVer(token);
        anyMethod();
        if (jwtTokenProvider.validateToken(token)) {
            System.out.println("User Authed");
        } else {
            System.out.println("User unauth");
        }
    }

    @GetMapping("/hello/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/authenticated")
    public String authenticated() {
        return "User Authenticated";
    }
}
