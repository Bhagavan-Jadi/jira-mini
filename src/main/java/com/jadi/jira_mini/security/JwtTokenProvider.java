package com.jadi.jira_mini.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key key;
    private final long jwtExpiration;


    public JwtTokenProvider(
            @Value("$jwt.secret") String secret,
            @Value("$jwt.expiration") long jwtExpiration
    ) {
        this.jwtExpiration = jwtExpiration;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String email) {

        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public String getEmailFromToken(String token) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {

        try{

            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;

        }catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }
}
