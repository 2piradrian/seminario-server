package com.group3.users.domain.dto.review.mapper.implementation;

import com.group3.entity.PageContent;
import com.group3.entity.Review;
import com.group3.users.domain.dto.review.request.GetPageReviewsByReviewedIdReq;
import com.group3.users.domain.dto.review.response.GetPageReviewsByReviewedIdRes;

import java.util.Map;

public class GetPageByReviewedMapper {

    public GetPageReviewsByReviewedIdReq toRequest(String token, String userId, Integer page, Integer size) {
        return GetPageReviewsByReviewedIdReq.create(
            token,
            userId,
            page,
            size
        );
    }

    public GetPageReviewsByReviewedIdRes toResponse(PageContent<Review> reviews) {
        return new GetPageReviewsByReviewedIdRes(
            reviews.getContent(),
            reviews.getNextPage()
        );
    }
}
