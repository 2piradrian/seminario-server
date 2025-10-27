package com.group3.user_profiles.domain.dto.review.mapper.implementation;

import com.group3.user_profiles.domain.dto.review.request.CreateReviewReq;

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

}
