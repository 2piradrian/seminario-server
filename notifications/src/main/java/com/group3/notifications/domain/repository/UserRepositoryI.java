package com.group3.notifications.domain.repository;

import com.group3.entity.Follow;
import com.group3.entity.User;

import java.util.List;

public interface UserRepositoryI {

    User auth(String token);

}
