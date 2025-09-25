package com.group3.profiles.domain.repository;

import com.group3.entity.User;

import java.util.List;

public interface UserRepositoryI {

    User getById(String userId);

    User getByEmail(String email);

    List<User> getByFullName(String name, String surname);

    User save(User user);

    User update(User user);

}
