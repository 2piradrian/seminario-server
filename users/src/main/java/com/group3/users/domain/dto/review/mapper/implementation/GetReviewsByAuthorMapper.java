package com.group3.users.domain.dto.review.mapper.implementation;

import com.group3.entity.PageContent;
import com.group3.entity.Review;
import com.group3.users.domain.dto.review.request.GetReviewsByAuthorReq;
import com.group3.users.domain.dto.review.response.GetReviewsByAuthorRes;

import java.util.Map;

public class GetReviewsByAuthorMapper {

    public GetReviewsByAuthorReq toRequest(String token, Integer page, Integer size) {
        return GetReviewsByAuthorReq.create(
            token,
            page,
            size
        );
    }

    public GetReviewsByAuthorRes toResponse(PageContent<Review> reviews) {
        return new GetReviewsByAuthorRes(
            reviews.getContent(),
            reviews.getNextPage()
        );
    }

}
