package com.example.Demo.service;

import com.example.Demo.entity.User;
import com.example.Demo.payload.LoginRequest;
import com.example.Demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User updateUser(Long userId, User user) throws Exception {
        User userDB = userRepository.findById(userId).orElse(null);
        if (userDB != null) {
            userDB.setFirstName(user.getFirstName());
            userDB.setLastName(user.getLastName());
            userDB.setUsername(user.getUsername());
            userDB.setEmail(user.getEmail());
            return userRepository.save(userDB);
        }

        return null;
    }
}
