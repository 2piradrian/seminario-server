package com.group3.users.domain.repository;

import com.group3.entity.UserProfile;

public interface ProfileRepositoryI {

    void create(String id, String email, String name, String surname, String secret);

    void active(String userId, String secret);

    UserProfile getById(String token, String userId);

}
