package com.group3.posts.domain.repository;

import com.group3.entity.User;

public interface UserRepositoryI {

    User auth(String token);

    User getById(String userId);

}
