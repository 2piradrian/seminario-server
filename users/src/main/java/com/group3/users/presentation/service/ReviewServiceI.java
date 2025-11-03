package com.group3.users.presentation.service;

import com.group3.users.domain.dto.review.request.*;
import com.group3.users.domain.dto.review.response.*;
import com.group3.users.domain.dto.review.request.GetPageReviewsByReviewedIdReq;
import com.group3.users.domain.dto.review.response.GetPageReviewsByReviewedIdRes;

public interface ReviewServiceI {

    CreateReviewRes create(CreateReviewReq dto);

    UpdateReviewRes update(UpdateReviewReq dto);

    void delete(DeleteReviewReq dto);

    GetReviewsByAuthorRes getReviewsByAuthor(GetReviewsByAuthorReq dto);

    GetPageReviewsByReviewedIdRes getReviewsByReviewedId(GetPageReviewsByReviewedIdReq dto);

}
