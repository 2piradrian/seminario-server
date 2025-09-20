package com.group3.users.presentation.service;

import com.group3.entity.Token;
import com.group3.entity.User;
import com.group3.users.config.helpers.JWTHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@AllArgsConstructor
public class AuthService implements AuthServiceI {

  private final PasswordEncoder passwordEncoder;

  private final JWTHelper jwtHelper;

  @Override
  public String hashPassword(String password) {
    return this.passwordEncoder.encode(password);
  }

  @Override
  public Boolean validatePassword(User user, String password) {
    return this.passwordEncoder.matches(password, user.getPassword());
  }

  @Override
  public Token createToken(User user) {
    return new Token(this.jwtHelper.createToken(user));
  }

  @Override
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

  @Override
  public String getSubject(String token) {
    return this.jwtHelper.getSubject(token);
  }

}
