package com.group3.profiles.data.repository;

import com.group3.entity.User;
import com.group3.profiles.data.users_server.repository.UsersServerRepositoryI;
import com.group3.profiles.data.users_server.responses.auth.AuthUserRes;
import com.group3.profiles.domain.repository.UserRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepository implements UserRepositoryI {

    private final UsersServerRepositoryI repository;

    @Override
    public User auth(String token) {
        AuthUserRes response = this.repository.auth(token);

        User user = new User();
        user.setId(response.getId());
        user.setEmail(response.getEmail());
        user.setRoles(response.getRoles());
        user.setStatus(response.getStatus());

        return user;
    }

}
