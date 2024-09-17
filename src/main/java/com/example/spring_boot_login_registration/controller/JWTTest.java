package com.example.spring_boot_login_registration.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTest {

    private static final String SECRET_KEY = "rw8i337g5fw1a5enessykuxfpeukb60teuqp1zr1p4stf25clp6sy4vmcxu2fxwg"; // Replace with your actual secret key

    public void jwtVer(String token) {
        try {
            // Decode the token
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Check claims
            Date now = new Date();
            System.out.println(claims.getExpiration().before(now));
            if (claims.getExpiration().before(now)) {
                System.out.println("Token expired.");
            } else {
                System.out.println("Token is valid.");
                System.out.println("Subject: " + claims.getSubject());
                System.out.println("Expiration: " + claims.getExpiration());
            }
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("Invalid token.");
        }
    }
}
