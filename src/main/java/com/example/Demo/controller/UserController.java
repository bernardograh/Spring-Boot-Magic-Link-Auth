package com.example.Demo.controller;

import com.example.Demo.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public List<User> getAllUsers() {
        return null;
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") Long userId) {
        return null;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return null;
    }

    @PutMapping("/userId")
    public User updateUser(@PathVariable("userId") Long userId, @RequestBody User user) {
        return null;
    }


}
