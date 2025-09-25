package com.group3.users.data.repository;

import com.group3.users.data.profiles_server.repository.ProfileServerRepositoryI;
import com.group3.users.domain.repository.ProfileRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class ProfileRepository implements ProfileRepositoryI {

    private final ProfileServerRepositoryI repository;

    public void create(String id, String email, String name, String surname) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("email", email);
        payload.put("name", name);
        payload.put("surname", surname);

        this.repository.create(payload);
    };

}
