package com.group3.profiles.data.datasource.postgres.repository;

import com.group3.entity.Status;
import com.group3.profiles.data.datasource.postgres.model.UserProfileModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostgresUserProfileRepositoryI extends JpaRepository<UserProfileModel, String> {

    Optional<UserProfileModel> findByEmail(String email);

    //TODO: FIX IT 
    @Query("""
        SELECT u
        FROM UserProfileModel u
        WHERE LOWER(CONCAT(u.name, ' ', u.surname)) LIKE LOWER(CONCAT('%', :fullName, '%'))
    """)
    Page<UserProfileModel> findByFullNameLike(
        @Param("fullName") String fullName,
        Pageable pageable
    );

}
