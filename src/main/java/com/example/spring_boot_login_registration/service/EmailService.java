package com.example.spring_boot_login_registration.service;

import com.example.spring_boot_login_registration.payload.LoginPayload;
import com.example.spring_boot_login_registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class EmailService {

    @Autowired
    private final JavaMailSender mailSender;
    private final UserRepository userRepository;

    @Value("$(spring.mail.username)")
    private String fromEmailId;

    public EmailService(JavaMailSender mailSender, UserRepository userRepository) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
    }

    public void sendTokenMail(LoginPayload loginPayload, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        System.out.println("LoginPayload: " + loginPayload.getUsernameOrEmail());
        System.out.println("User exists: " + userRepository.existsByEmail(loginPayload.getUsernameOrEmail()));
        if (userRepository.existsByEmail(loginPayload.getUsernameOrEmail())) {
            message.setFrom(fromEmailId);
            message.setTo(loginPayload.getUsernameOrEmail());
            message.setText("Follow this link to confirm your email\nhttp://localhost:8080/api/auth/verify?token=" + token);
            message.setSubject("Confirm email");
            mailSender.send(message);
            System.out.println("Mail Sent...");
        } else {
            System.out.println("Mail send fail");
        }

    }
}
