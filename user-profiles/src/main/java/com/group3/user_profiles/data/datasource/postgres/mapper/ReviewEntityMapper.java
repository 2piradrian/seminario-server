package com.group3.user_profiles.data.datasource.postgres.mapper;

import com.group3.entity.Review;
import com.group3.user_profiles.data.datasource.postgres.model.ReviewModel;
import com.group3.user_profiles.data.datasource.postgres.model.UserProfileModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewEntityMapper {

    public static Review toDomain(ReviewModel reviewModel) {
        if (reviewModel == null) return null;

        return Review.builder()
                .author(UserProfileEntityMapper.toDomain(reviewModel.getAuthor()))
                .review(reviewModel.getReview())
                .rating(reviewModel.getRating())
                .build();
    }

    public static ReviewModel toModel(Review review, UserProfileModel reviewedUserModel) {
        if (review == null) return null;

        ReviewModel model = new ReviewModel();
        model.setAuthor(UserProfileEntityMapper.toModel(review.getAuthor()));
        model.setReviewedUser(reviewedUserModel);
        model.setReview(review.getReview());
        model.setRating(review.getRating());
        return model;
    }

    public static List<Review> toDomain(List<ReviewModel> reviewModels) {
        if (reviewModels == null) return Collections.emptyList();
        return reviewModels.stream()
                .map(ReviewEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static List<ReviewModel> toModel(List<Review> reviews, UserProfileModel reviewedUserModel) {
        if (reviews == null) return Collections.emptyList();
        return reviews.stream()
                .map(r -> ReviewEntityMapper.toModel(r, reviewedUserModel))
                .collect(Collectors.toList());
    }
}
