package com.dev.diego.backend.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Integer expiration;

    public String generateToken(String email) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(email);
            return JWT.create().withIssuer("Backend").withSubject(email).sign(algorithm);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }    
}
