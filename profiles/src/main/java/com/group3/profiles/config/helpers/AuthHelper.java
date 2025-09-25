package com.group3.profiles.config.helpers;

import com.group3.entity.Token;
import com.group3.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class AuthHelper {

    private final PasswordEncoder passwordEncoder;
    private final JWTHelper jwtHelper;

    public String hashPassword(String password) {
        return this.passwordEncoder.encode(password);
    }

    public Boolean validatePassword(User user, String password) {
        return this.passwordEncoder.matches(password, user.getPassword());
    }

    public Token createToken(User user) {
        return new Token(this.jwtHelper.createToken(user));
    }

    public String validateToken(String rawHeader) {

        if (rawHeader == null || rawHeader.isBlank()) {
            return null;
        }

        if (!rawHeader.startsWith("Bearer ")) {
            return null;
        }

        String tokenValue = rawHeader.substring(7).trim();


        try {
            if (this.jwtHelper.validateToken(tokenValue)) {
                return tokenValue;
            }
        }
        catch (Exception e) {
            return null;
        }

        return null;
    }

    public String getSubject(String token) {
        return this.jwtHelper.getSubject(token);
    }
}