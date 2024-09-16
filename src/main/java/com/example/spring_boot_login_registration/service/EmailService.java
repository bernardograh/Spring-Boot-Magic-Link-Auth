package com.example.spring_boot_login_registration.service;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Service
//@Component
//public class EmailService {
//
//    @Autowired
//    private final JavaMailSender mailSender;
//
//    @Value("$(spring.mail.username)")
//    private String fromEmailId;
//
//    public EmailService(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    public void sendTokenMail(String toEmail, String tokenKey) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(fromEmailId);
//        message.setTo(toEmail);
//        message.setText("Follow this link to confirm your email\nhttp://localhost:8080/authenticated?token="+tokenKey);
//        message.setSubject("Confirm email");
//
//        mailSender.send(message);
//        System.out.println("Mail Sent...");
//    }
//}
