package com.group3.notifications.domain.repository;

import com.group3.entity.User;

public interface UserRepositoryI {

    User auth(String token);

}
