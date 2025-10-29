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

        return Review.builder()
                .id(model.getId())
                .reviewedId(model.getReviewedId() != null ? model.getReviewedId() : null)
                .reviewerUser(model.getReviewerUserId() != null ? UserProfile.builder().id(model.getReviewerUserId()).build() : null)
                .review(model.getReview())
                .rating(model.getRating())
                .build();

    }

    public static ReviewModel toModel(Review review) {
        if (review == null) return null;

        ReviewModel model = new ReviewModel();
        model.setId(review.getId());
        model.setReviewedId(review.getReviewedId() != null ? review.getReviewedId() : null);
        model.setReviewerUserId(review.getReviewerUser() != null ? review.getReviewerUser().getId() : null);
        model.setReview(review.getReview());
        model.setRating(review.getRating());
        return model;
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
