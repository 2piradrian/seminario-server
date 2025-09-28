package com.group3.posts.domain.repository;

import com.group3.entity.TokenClaims;
import com.group3.entity.User;

public interface AuthRepositoryI {

    TokenClaims auth(String token);

    User getById(String userId);
}
