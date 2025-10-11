package com.group3.profiles.data.datasource.postgres.repository;

import com.group3.profiles.data.datasource.postgres.model.UserProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostgresUserProfileRepositoryI extends JpaRepository<UserProfileModel, String> {

    Optional<UserProfileModel> findByEmail(String email);

    //TODO: FIX IT 
    @Query("""
        SELECT u
        FROM UserProfileModel u
        WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :firstName, '%'))
        AND LOWER(u.surname) LIKE LOWER(CONCAT('%', :surName, '%'))
    """)
    List<UserProfileModel> findByFullNameLike(@Param("name") String name, @Param("surname") String surname);

    @Query(
       value = "SELECT following_id FROM user_following WHERE user_id = :userId ORDER BY following_id ASC",
       countQuery = "SELECT COUNT(*) FROM user_following WHERE user_id = :userId",
       nativeQuery = true
    )
    Page<String> findFollowing(
            @Param("userId") String userId,
            Pageable pageable
    );

}
