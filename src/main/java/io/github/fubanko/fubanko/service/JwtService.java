package io.github.fubanko.fubanko.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    @Value("${jwt.security.secret-key}")
    private String secretKey;


    public String generateToken(Authentication authentication) {

        Instant now = Instant.now();
        var expiration = 3600L;

        var scopes = authentication.getAuthorities().toString();

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.create()
                    .withIssuer("fubanko")
                    .withSubject(authentication.getName())
                    .withIssuedAt(now)
                    .withExpiresAt(now.plusSeconds(expiration))
                    .withClaim("scopes", scopes)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Could not generate token", exception);
        }
    }

    public String validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer("fubanko")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid Token", exception);
        }
    }




}
