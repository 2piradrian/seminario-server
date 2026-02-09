package com.group3.users.data.datasource.postgres.repository;

import com.group3.users.data.datasource.postgres.model.FollowModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import jakarta.transaction.Transactional;

public interface PostgresFollowRepositoryI extends JpaRepository<FollowModel, String> {

    @Query("""
        SELECT f
        FROM FollowModel f
        WHERE f.followerId = :followerId
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

    @Query("SELECT f FROM FollowModel f WHERE f.followerId = :followerId")
    List<FollowModel> findAllByFollowerId(@Param("followerId") String followerId);

    @Query("SELECT f FROM FollowModel f WHERE f.followedId = :followedId")
    List<FollowModel> findAllByFollowedId(@Param("followedId") String followedId);

    @Query("SELECT COUNT(f) FROM FollowModel f WHERE f.followerId = :followerId")
    Integer countByFollowerId(@Param("followerId") String followerId);

    @Query("SELECT COUNT(f) FROM FollowModel f WHERE f.followedId = :followedId")
    Integer countByFollowedId(@Param("followedId") String followedId);

    @Modifying
    @Transactional
    @Query("DELETE FROM FollowModel f WHERE f.followerId = :followerId")
    void deleteByFollowerId(@Param("followerId") String followerId);

    @Modifying
    @Transactional
    @Query("DELETE FROM FollowModel f WHERE f.followedId = :followedId")
    void deleteByFollowedId(@Param("followedId") String followedId);
}
