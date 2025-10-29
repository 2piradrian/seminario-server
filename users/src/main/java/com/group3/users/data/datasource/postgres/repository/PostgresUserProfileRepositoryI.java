package com.group3.users.data.datasource.postgres.repository;

import com.group3.entity.Status;
import com.group3.users.data.datasource.postgres.model.UserProfileModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostgresUserProfileRepositoryI extends JpaRepository<UserProfileModel, String> {

    // ======== Search ========

    Optional<UserProfileModel> findByEmail(String email);

    List<UserProfileModel> findAllByIdIn(List<String> ids);


    // ======== (followers / following) ========

    @Query(
            value = "SELECT user_id FROM user_following WHERE following_id = :userId ORDER BY user_id ASC",
            countQuery = "SELECT COUNT(*) FROM user_following WHERE following_id = :userId",
            nativeQuery = true
    )
    Page<String> findFollowers(
            @Param("userId") String userId,
            Pageable pageable
    );

    @Query(
            value = "SELECT following_id FROM user_following WHERE user_id = :userId ORDER BY following_id ASC",
            countQuery = "SELECT COUNT(*) FROM user_following WHERE user_id = :userId",
            nativeQuery = true
    )
    Page<String> findFollowing(
            @Param("userId") String userId,
            Pageable pageable
    );


    // ======== Count ========

    @Query(
            value = "SELECT COUNT(*) FROM user_following WHERE user_id = :id",
            nativeQuery = true
    )
    Integer countFollowing(@Param("id") String id);

    @Query(
            value = "SELECT COUNT(*) FROM user_following WHERE following_id = :id",
            nativeQuery = true
    )
    Integer countFollowers(@Param("id") String id);

}
