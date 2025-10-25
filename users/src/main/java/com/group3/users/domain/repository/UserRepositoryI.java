package com.group3.users.domain.repository;

import com.group3.entity.ContentType;
import com.group3.entity.User;

import java.util.List;

public interface UserRepositoryI {

    User getById(String userId);

    User getByEmail(String email);

    List<User> getAllStaff();

    User save(User user);

    User update(User user);

}
