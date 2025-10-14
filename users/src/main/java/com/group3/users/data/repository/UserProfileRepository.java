package com.group3.users.data.repository;

import com.group3.users.data.datasource.user_profiles_server.repository.UserProfileServerRepositoryI;
import com.group3.users.domain.repository.ProfileRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class UserProfileRepository implements ProfileRepositoryI {

    private final UserProfileServerRepositoryI repository;

    @Override
    public void create(String id, String email, String name, String surname, String secret) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("email", email);
        payload.put("name", name);
        payload.put("surname", surname);
        payload.put("secret", secret);

        this.repository.create(payload);
    }

    @Override
    public void active(String userId) {
        this.repository.active(userId);
    }

}
