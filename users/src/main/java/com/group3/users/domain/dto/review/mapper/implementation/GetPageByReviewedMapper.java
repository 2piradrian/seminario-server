package com.group3.users.domain.dto.review.mapper.implementation;

import com.group3.entity.PageContent;
import com.group3.entity.Review;
import com.group3.users.domain.dto.review.request.GetPageReviewsByReviewedIdReq;
import com.group3.users.domain.dto.review.response.GetPageReviewsByReviewedIdRes;

import java.util.Map;

public class GetPageByReviewedMapper {

    public GetPageReviewsByReviewedIdReq toRequest(String token, Map<String, Object> payload) {
        return GetPageReviewsByReviewedIdReq.create(
            token,
            (String) payload.get("userId"),
            (Integer) payload.get("page"),
            (Integer) payload.get("size")
        );
    }

    public GetPageReviewsByReviewedIdRes toResponse(PageContent<Review> reviews) {
        return new GetPageReviewsByReviewedIdRes(
            reviews.getContent(),
            reviews.getNextPage()
        );
    }
}
