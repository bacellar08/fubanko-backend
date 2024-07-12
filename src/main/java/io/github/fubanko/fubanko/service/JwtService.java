package io.github.fubanko.fubanko.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(Authentication authentication) {

        Instant now = Instant.now();
        var expiration = 3600L;

        var scopes = authentication.getAuthorities().toString();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("fubanko")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiration))
                .subject(authentication.getName())
                .claim("scopes", scopes)
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


}
