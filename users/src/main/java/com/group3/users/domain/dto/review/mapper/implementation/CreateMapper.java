package com.group3.users.domain.dto.review.mapper.implementation;

import com.group3.entity.Review;
import com.group3.users.domain.dto.review.request.CreateReviewReq;
import com.group3.users.domain.dto.review.response.CreateReviewRes;

import java.util.Map;

public class CreateMapper {

    public CreateReviewReq toRequest(String token, Map<String, Object> payload) {
        return CreateReviewReq.create(
            (String) payload.get("reviewedUserId"),
            (String) payload.get("review"),
            payload.get("rating") != null ? ((Number) payload.get("rating")).floatValue() : null,
            token
        );
    }

    public CreateReviewRes toResponse(Review review) {
        return new CreateReviewRes(
                review.getId()
        );
    }

}
