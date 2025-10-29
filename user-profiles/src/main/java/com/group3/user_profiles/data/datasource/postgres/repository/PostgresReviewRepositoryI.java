package com.group3.user_profiles.data.datasource.postgres.repository;

import com.group3.user_profiles.data.datasource.postgres.model.ReviewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostgresReviewRepositoryI extends JpaRepository<ReviewModel, String> {

    @Query("""
        SELECT r
        FROM ReviewModel r
        WHERE r.reviewerUserId = :reviewerId
        ORDER BY r.id DESC
    """)
    Page<ReviewModel> findByReviewerId(
            @Param("reviewerId") String reviewerId,
            Pageable pageable
    );

}
