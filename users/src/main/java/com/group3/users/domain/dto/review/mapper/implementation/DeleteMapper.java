package com.group3.users.domain.dto.review.mapper.implementation;


import com.group3.users.domain.dto.review.request.DeleteReviewReq;

import java.util.Map;

public class DeleteMapper {

    public DeleteReviewReq toRequest(String token, Map<String, Object> payload) {
        return DeleteReviewReq.create(
            (String) payload.get("id"),
            token
        );
    }

}
