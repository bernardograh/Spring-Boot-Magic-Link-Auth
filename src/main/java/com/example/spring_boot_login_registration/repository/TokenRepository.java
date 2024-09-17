package com.example.spring_boot_login_registration.repository;

import com.example.spring_boot_login_registration.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String> {
}
