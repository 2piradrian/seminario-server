package com.group3.users.data.datasource.postgres.mapper;

import com.group3.entity.Review;
import com.group3.entity.UserProfile;
import com.group3.users.data.datasource.postgres.model.ReviewModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewEntityMapper {

    public static Review toDomain(ReviewModel model) {
        if (model == null) return null;

        return new Review(
                model.getId(),
                model.getReviewedId(),
                model.getReviewerUserId() != null ? UserProfile.builder().id(model.getReviewerUserId()).build() : null,
                model.getReview(),
                model.getRating(),
                model.getCreatedAt(),
                model.getUpdatedAt(),
                model.getStatus()
        );
    }

    public static ReviewModel toModel(Review review) {
        if (review == null) return null;

        return new ReviewModel(
                review.getId(),
                review.getReviewedId(),
                review.getReviewerUser() != null ? review.getReviewerUser().getId() : null,
                review.getReview(),
                review.getRating(),
                review.getCreatedAt(),
                review.getUpdatedAt(),
                review.getStatus()
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
