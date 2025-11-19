package com.group3.users.domain.dto.review.mapper.implementation;

import com.group3.entity.Review;
import com.group3.users.domain.dto.review.request.UpdateReviewReq;
import com.group3.users.domain.dto.review.response.UpdateReviewRes;

import java.util.Map;

public class UpdateMapper {

    public UpdateReviewReq toRequest(String token, String id, Map<String, Object> payload) {
        return UpdateReviewReq.create(
            id,
            (String) payload.get("review"),
            payload.get("rating") != null ? ((Number) payload.get("rating")).floatValue() : null,
            token
        );
    }

    public UpdateReviewRes toResponse(Review review) {
        return new UpdateReviewRes(
                review.getId()
        );
    }

}
