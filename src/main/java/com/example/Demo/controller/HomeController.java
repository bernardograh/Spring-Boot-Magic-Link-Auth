package com.example.Demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    @GetMapping
    public String Home(Principal principal) {
        return "Hello, Authed principal "+ principal.getName();
    }
}
