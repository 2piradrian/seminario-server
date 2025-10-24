package com.group3.results.data.repository;

import com.group3.entity.User;
import com.group3.results.data.datasource.users_server.repository.UsersServerRepositoryI;
import com.group3.results.data.datasource.users_server.responses.AuthUserRes;
import com.group3.results.domain.repository.UserRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepository implements UserRepositoryI {

    private final UsersServerRepositoryI repository;

    // ======== Authentication ========

    @Override
    public User auth(String token) {
        AuthUserRes response = this.repository.auth(token);

        User user = new User();
        user.setId(response.getId());
        user.setEmail(response.getEmail());
        user.setRol(response.getRol());
        user.setStatus(response.getStatus());

        return user;
    }

}
