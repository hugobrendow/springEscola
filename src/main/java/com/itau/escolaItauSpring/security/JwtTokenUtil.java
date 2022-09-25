package com.itau.escolaItauSpring.security;

import com.google.common.net.HttpHeaders;
import com.itau.escolaItauSpring.model.Usuario;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenUtil {

    @Value("${security.jwt.token.secret-key")
    private String secretKey;

    @Value("${security.jwt.token.token-expiration}")
    private long tokenExpiration;

    public String getToken(HttpServletRequest request) {
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return parseClaims(token).getExpiration();
    }

    public String getUsername(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String generateAcessToken(UserDetails user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + tokenExpiration);

        return Jwts.builder()
//                .setSubject(user.getUsername())
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}