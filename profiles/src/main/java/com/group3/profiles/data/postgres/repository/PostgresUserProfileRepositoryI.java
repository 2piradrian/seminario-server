package com.group3.profiles.data.postgres.repository;

import com.group3.profiles.data.postgres.model.UserProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostgresUserProfileRepositoryI extends JpaRepository<UserProfileModel, String> {

    Optional<UserProfileModel> findByEmail(String email);

    @Query("""
        SELECT u
        FROM UserProfileModel u
        WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :firstName, '%'))
        AND LOWER(u.surname) LIKE LOWER(CONCAT('%', :surName, '%'))
    """)
    List<UserProfileModel> findByFullNameLike(@Param("name") String name, @Param("surname") String surname);

}
