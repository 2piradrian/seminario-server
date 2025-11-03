package com.group3.users.data.datasource.postgres.repository;

import com.group3.users.data.datasource.postgres.model.UserProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostgresUserProfileRepositoryI extends JpaRepository<UserProfileModel, String> {

    // ======== Search ========

    List<UserProfileModel> findAllByIdIn(List<String> ids);

}
