package com.group3.user_profiles.data.datasource.postgres.mapper;

import com.group3.entity.Review;
import com.group3.user_profiles.data.datasource.postgres.model.ReviewModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewEntityMapper {

    public static Review toDomain(ReviewModel model) {
        if (model == null) return null;

        return new Review(
                model.getId(),
                UserProfileEntityMapper.toDomain(model.getReviewedUser()),
                UserProfileEntityMapper.toDomain(model.getReviewerUser()),
                model.getReview(),
                model.getRating()
        );
    }

    public static ReviewModel toModel(Review review) {
        if (review == null) return null;

        return new ReviewModel(
                review.getId(),
                UserProfileEntityMapper.toModel(review.getReviewedUser()),
                UserProfileEntityMapper.toModel(review.getReviewerUser()),
                review.getReview(),
                review.getRating()
        );
    }

    public static List<Review> toDomain(List<ReviewModel> models) {
        if (models == null) return Collections.emptyList();

        return models.stream()
                .map(ReviewEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static List<ReviewModel> toModel(List<Review> reviews) {
        if (reviews == null) return Collections.emptyList();

        return reviews.stream()
                .map(ReviewEntityMapper::toModel)
                .collect(Collectors.toList());
    }

}
