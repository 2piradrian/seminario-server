package com.group3.users.domain.dto.review.mapper.implementation;

import com.group3.entity.Post;
import com.group3.entity.Review;
import com.group3.users.domain.dto.review.request.GetReviewByIdReq;
import com.group3.users.domain.dto.review.response.GetReviewByIdRes;

public class GetByIdMapper {

    public GetReviewByIdReq toRequest(String reviewId, String token) {
        return GetReviewByIdReq.create(
            reviewId,
            token
        );
    }

    public GetReviewByIdRes toResponse(Review review) {
        return new GetReviewByIdRes(
            review.getId(),
            review.getReviewedId(),
            review.getReviewerUser().getId(),
            review.getReview(),
            review.getRating(),
            review.getCreatedAt(),
            review.getUpdatedAt(),
            review.getStatus()
        );
    }

}
