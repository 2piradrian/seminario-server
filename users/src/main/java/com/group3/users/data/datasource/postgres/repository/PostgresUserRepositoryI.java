package com.group3.users.data.datasource.postgres.repository;

import com.group3.users.data.datasource.postgres.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostgresUserRepositoryI extends JpaRepository<UserModel, String> {

    Optional<UserModel> findByEmail(String email);

}
