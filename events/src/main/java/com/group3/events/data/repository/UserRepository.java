package com.group3.events.data.repository;

import com.group3.entity.User;
import com.group3.events.data.datasource.users_server.repository.UsersServerRepositoryI;
import com.group3.events.data.datasource.users_server.responses.AuthUserRes;
import com.group3.events.data.datasource.users_server.responses.GetUserByIdRes;
import com.group3.events.domain.repository.UserRepositoryI;
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
        user.setRole(response.getRol());
        user.setStatus(response.getStatus());

        return user;
    }


    // ======== Single User Retrieval ========

    @Override
    public User getById(String userId) {
        GetUserByIdRes response = this.repository.getById(userId);

        User user = new User();
        user.setId(response.getId());
        user.setEmail(response.getEmail());
        user.setRole(response.getRol());
        user.setStatus(response.getStatus());

        return user;
    }

}
