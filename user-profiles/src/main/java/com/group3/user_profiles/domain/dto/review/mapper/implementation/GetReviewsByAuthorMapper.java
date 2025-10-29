package com.group3.user_profiles.domain.dto.review.mapper.implementation;

import com.group3.entity.PageContent;
import com.group3.entity.Review;
import com.group3.user_profiles.domain.dto.review.request.GetReviewsByAuthorReq;
import com.group3.user_profiles.domain.dto.review.response.GetReviewsByAuthorRes;

import java.util.Map;

public class GetReviewsByAuthorMapper {

    public GetReviewsByAuthorReq toRequest(String token, Map<String, Object> payload) {
        return GetReviewsByAuthorReq.create(
            token,
            (Integer) payload.get("page"),
            (Integer) payload.get("size")
        );
    }

    public GetReviewsByAuthorRes toResponse(PageContent<Review> reviews) {
        return new GetReviewsByAuthorRes(
            reviews.getContent(),
            reviews.getNextPage()
        );
    }

}
