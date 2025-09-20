package com.group3.users.presentation.service;

import com.group3.entity.Token;
import com.group3.entity.User;

public interface AuthServiceI {

  String hashPassword(String password);

  Boolean validatePassword(User user, String password);

  String validateToken(String token);

  String getSubject(String token);

  Token createToken(User user);

}
