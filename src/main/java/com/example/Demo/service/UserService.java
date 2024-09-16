package com.example.Demo.service;

import com.example.Demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(Long userId) throws Exception;

    User updateUser(Long userId, User user) throws Exception;
}
