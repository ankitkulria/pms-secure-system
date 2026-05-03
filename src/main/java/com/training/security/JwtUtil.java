package com.training.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET="thisisverylongsecretkeyusedforjwtlawdeyanbhujyamn";
    private Key getSignKey()
    {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String email)
    {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*15))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
