package com.group3.users.domain.repository;

import com.group3.entity.User;

public interface UserRepositoryI {

  User getById(String userId);

  User getByEmail(String email);

  User getByFullname(String name, String surname);

  User save(User user);

  User update(User user);
}
