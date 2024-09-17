package com.example.spring_boot_login_registration.repository;

import com.example.spring_boot_login_registration.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {

    Optional<Token> findByToken(String token);
}
