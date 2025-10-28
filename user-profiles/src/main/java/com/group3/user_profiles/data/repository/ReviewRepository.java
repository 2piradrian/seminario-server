package com.group3.user_profiles.data.repository;

import com.group3.entity.Review;
import com.group3.user_profiles.data.datasource.postgres.mapper.ReviewEntityMapper;
import com.group3.user_profiles.data.datasource.postgres.model.ReviewModel;
import com.group3.user_profiles.data.datasource.postgres.repository.PostgresReviewRepositoryI;
import com.group3.user_profiles.domain.repository.ReviewRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ReviewRepository implements ReviewRepositoryI {

    private final PostgresReviewRepositoryI repository;


    // ======== Get Review by ID ========

    @Override
    public Review getById(String reviewId) {
        ReviewModel reviewModel = this.repository.findById(reviewId).orElse(null);
        return reviewModel != null ? ReviewEntityMapper.toDomain(reviewModel) : null;
    }


    // ======== Save and Update ========

    @Override
    public Review save(Review review) {
        ReviewModel reviewModel = ReviewEntityMapper.toModel(review);
        ReviewModel saved = this.repository.save(reviewModel);
        return ReviewEntityMapper.toDomain(saved);
    }

    @Override
    public Review update(Review review) {
        ReviewModel reviewModel = ReviewEntityMapper.toModel(review);
        ReviewModel updated = this.repository.save(reviewModel);
        return ReviewEntityMapper.toDomain(updated);
    }


    // ======== Delete ========

    @Override
    public void delete(String reviewId) {
        this.repository.deleteById(reviewId);
    }
}
