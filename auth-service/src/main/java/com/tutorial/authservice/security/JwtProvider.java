package com.tutorial.authservice.security;

import com.tutorial.authservice.dto.RequestDto;
import com.tutorial.authservice.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    RouteValidator routeValidator;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims = Jwts.claims().setSubject(user.getUserName());
        claims.put("id", user.getId());
        claims.put("role", user.getRole());
        Date now = new Date();
        Date exp = new Date(now.getTime() + 3600000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validate(String token, RequestDto dto) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        }catch (Exception e){
            return false;
        }
        if(!isAdmin(token) && routeValidator.isAdminPath(dto))
            return false;
        if(!isDirectorCareer(token) && routeValidator.isDirectorCareer(dto))
            return false;
        if(!isBedelia(token) && routeValidator.isBedelia(dto))
            return false;
        return true;
    }

    public String getUserNameFromToken(String token){
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        }catch (Exception e) {
            return "bad token";
        }
    }

    private boolean isAdmin(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("role").equals("ROLE_ADMIN");
    }

    private boolean isDirectorCareer(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("role").equals("ROLE_DIRECTOR_CARRERA");
    }

    private boolean isBedelia(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("role").equals("ROLE_BEDELIA");
    }


}
