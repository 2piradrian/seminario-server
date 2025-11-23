package com.group3.users.domain.dto.review.mapper.implementation;


import com.group3.users.domain.dto.review.request.DeleteReviewReq;

import java.util.Map;

public class DeleteMapper {

    public DeleteReviewReq toRequest(String token, String id) {
        return DeleteReviewReq.create(
            id,
            token
        );
    }

}
