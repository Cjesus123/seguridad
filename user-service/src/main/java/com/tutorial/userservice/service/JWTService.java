package com.tutorial.userservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class JWTService {

    private static final String SECRET_KEY = "wIXZCWObK5oSdZriKq17XKf77UanlW/ZP/X8A7h50YPlMKcNNWIYsA+B1yJdGCHzpA02RQHqeu3JpNsI150oeQ==";

    public String getUsernameFromToken(String tokenWithBearer) {

        String jwtToken = tokenWithBearer.replace("Bearer ", "");
        String[] parts = jwtToken.split("\\.");
        String encodedBody = parts[1];
        byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedBody);
        String decodedBody = new String(decodedBytes, StandardCharsets.UTF_8);
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(new SecretKeySpec(SECRET_KEY.getBytes(), "HMACSHA256"))
                .build()
                .parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return claims.getSubject();
    }
}
