package com.example.BloodDonationSupportSystem.service;

import java.util.Date;
import java.util.function.Function;

import com.example.BloodDonationSupportSystem.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtService {
    private final String SECRET_KEY = "";

    public String extractToken(String token) {
        return extractToken(token, Claims::getSubject);
    }

    public <T> T extractToken(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(getClaims(token));
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, String username) {
        return extractToken(token).equals(username) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    public String generateToken(UserEntity user) {
        return Jwts.builder().setSubject(user.)

    }

}
