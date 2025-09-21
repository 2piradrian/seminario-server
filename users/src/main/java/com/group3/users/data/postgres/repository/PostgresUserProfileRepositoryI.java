package com.group3.users.data.postgres.repository;

import com.group3.users.data.postgres.model.UserProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresUserProfileRepositoryI extends JpaRepository<UserProfileModel, String> {

}
