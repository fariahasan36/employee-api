package com.faria.employee_api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY =
            "my-secret-key-my-secret-key-my-secret-key";

    public String generateToken(){

        SecretKey key = Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes()
        );

        return Jwts.builder()
                .subject("employee-api-user")
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 3600000)
                )
                .signWith(key)
                .compact();
    }


    public boolean isValid(String token){

        try {

            SecretKey key = Keys.hmacShaKeyFor(
                    SECRET_KEY.getBytes()
            );


            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);


            return true;

        } catch(Exception e){

            return false;
        }
    }
}