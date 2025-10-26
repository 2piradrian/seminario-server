package com.group3.users.data.datasource.postgres.repository;

import com.group3.entity.Role;
import com.group3.entity.Status;
import com.group3.users.data.datasource.postgres.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostgresUserRepositoryI extends JpaRepository<UserModel, String> {

    Optional<UserModel> findByEmail(String email);

    @Query("""
        SELECT u FROM UserModel u 
        WHERE u.role <> :roleExcluded
        AND u.status = :activeStatus
    """)
    List<UserModel> findWithExcludedRole(
        @Param("roleExcluded") Role roleExcluded,
        @Param("activeStatus") Status activeStatus
    );

}
