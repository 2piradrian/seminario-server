package com.group3.user_profiles.domain.dto.review.mapper.implementation;

import com.group3.user_profiles.domain.dto.review.request.UpdateReviewReq;

import java.util.Map;

public class UpdateMapper {

    public UpdateReviewReq toRequest(String token, Map<String, Object> payload) {
        return UpdateReviewReq.create(
            (String) payload.get("id"),
            (String) payload.get("review"),
            payload.get("rating") != null ? ((Number) payload.get("rating")).floatValue() : null,
            token
        );
    }

}
