package com.group3.users.data.datasource.postgres.repository;

import com.group3.entity.Status;
import com.group3.users.data.datasource.postgres.model.ReviewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostgresReviewRepositoryI extends JpaRepository<ReviewModel, String> {

    @Query("""
        SELECT r FROM ReviewModel r
        WHERE r.id = :id AND r.status = 'ACTIVE'
    """)
    Optional<ReviewModel> findById(@Param("id") String id);

    @Query("""
        SELECT r
        FROM ReviewModel r
        WHERE r.reviewerUserId = :reviewerId
        AND r.status = 'ACTIVE'
        ORDER BY r.createdAt DESC
    """)
    Page<ReviewModel> findByReviewerId(
            @Param("reviewerId") String reviewerId,
            Pageable pageable
    );

    @Query("""
        SELECT r
        FROM ReviewModel r
        WHERE r.reviewedId = :reviewedUserId
        AND r.status = 'ACTIVE'
        ORDER BY r.createdAt DESC
    """)
    Page<ReviewModel> findByReviewedUserId(
            @Param("reviewedUserId") String reviewedUserId,
            Pageable pageable
    );

}
