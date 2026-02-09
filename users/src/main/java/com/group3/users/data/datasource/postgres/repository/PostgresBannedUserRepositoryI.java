package com.group3.users.data.datasource.postgres.repository;

import com.group3.users.data.datasource.postgres.model.BannedUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostgresBannedUserRepositoryI extends JpaRepository<BannedUserModel, String> {
    Optional<BannedUserModel> findByEmail(String email);
}
