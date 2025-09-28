package com.group3.posts.data.repository;

import com.group3.entity.TokenClaims;
import com.group3.entity.User;
import com.group3.posts.data.auth_server.AuthServerRepositoryI;
import com.group3.posts.domain.repository.AuthRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class AuthRepository implements AuthRepositoryI{

    private final AuthServerRepositoryI authServerRepository;

    @Override
    public TokenClaims auth(String token) {
        Optional<TokenClaims> claims = this.authServerRepository.auth(token);

        return claims.orElse(null);
    }

    @Override
    public User getById(String userId) {
        Optional<User> user = this.authServerRepository.getById(userId);

        return user.orElse(null);
    }

}
