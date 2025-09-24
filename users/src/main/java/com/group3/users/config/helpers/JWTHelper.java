package com.group3.users.config.helpers;

import com.group3.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
public class JWTHelper {

    @Value("${application.jwt.secret}")
    private String secret;

    @Value("${application.jwt.expiration}")
    private Long expiration;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(this.secret.getBytes(StandardCharsets.UTF_8));
    }

    private Date getExpirationDate(String token) {
        return this.getClaims(token, Claims::getExpiration);
    }

    private <T> T getClaims(String token, Function<Claims, T> resolver) {
        return resolver.apply(this.parseToken(token));
    }

    public String getSubject(String token) {
        return this.getClaims(token, Claims::getSubject);
    }

    private Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String createToken(User user) {
        final var now = new Date();
        final var expirationDate = new Date(now.getTime() + this.expiration);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(this.getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Date expirationDate = this.getExpirationDate(token);
            return expirationDate.after(new Date());
        }
        catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
