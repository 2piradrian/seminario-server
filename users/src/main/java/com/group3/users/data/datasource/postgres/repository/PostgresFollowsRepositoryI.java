package com.group3.users.data.datasource.postgres.repository;

import com.group3.users.data.datasource.postgres.model.FollowModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostgresFollowsRepositoryI extends JpaRepository<FollowModel, String> {

    @Query("""
        SELECT f
        FROM FollowModel f
        WHERE f.profile.id = :followerId
        ORDER BY f.id DESC
    """)
    Page<FollowModel> findByFollowerId(
            @Param("followerId") String followerId,
            Pageable pageable
    );

    @Query("""
        SELECT f
        FROM FollowModel f
        WHERE f.followedId = :followedId
        ORDER BY f.id DESC
    """)
    Page<FollowModel> findByFollowedId(
            @Param("followedId") String followedId,
            Pageable pageable
    );

}
