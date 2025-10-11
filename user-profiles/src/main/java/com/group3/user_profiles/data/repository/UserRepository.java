package com.group3.user_profiles.data.repository;

import com.group3.entity.User;
import com.group3.user_profiles.data.datasource.users_server.repository.UsersServerRepositoryI;
import com.group3.user_profiles.data.datasource.users_server.responses.AuthUserRes;
import com.group3.user_profiles.data.datasource.users_server.responses.GetUserByIdRes;
import com.group3.user_profiles.domain.repository.UserRepositoryI;
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

    @Override
    public User getById(String userId) {
        GetUserByIdRes response = this.repository.getById(userId);

        User user = new User();
        user.setId(response.getId());
        user.setEmail(response.getEmail());
        user.setRoles(response.getRoles());
        user.setStatus(response.getStatus());

        return user;
    }

}
