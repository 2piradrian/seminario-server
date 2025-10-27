package com.group3.user_profiles.data.datasource.postgres.repository;

import com.group3.user_profiles.data.datasource.postgres.model.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresReviewRepositoryI extends JpaRepository<ReviewModel, String> {
}
