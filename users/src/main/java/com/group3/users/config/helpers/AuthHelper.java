package com.group3.users.config.helpers;

import com.group3.entity.Token;
import com.group3.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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

    public String validateToken(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }

        if (!token.startsWith("Bearer ")) {
            return null;
        }

        String tokenValue = token.replace("Bearer ", "");
        if (this.jwtHelper.validateToken(tokenValue)) {
            return tokenValue;
        }

        return null;
    }

    public String getSubject(String token) {
        return this.jwtHelper.getSubject(token);
    }

}
