package com.group3.user_profiles.presentation.service;

import com.group3.user_profiles.domain.dto.review.request.CreateReviewReq;
import com.group3.user_profiles.domain.dto.review.request.DeleteReviewReq;
import com.group3.user_profiles.domain.dto.review.request.UpdateReviewReq;
import com.group3.user_profiles.domain.dto.review.response.CreateReviewRes;
import com.group3.user_profiles.domain.dto.review.response.UpdateReviewRes;

public interface ReviewServiceI {

    CreateReviewRes create(CreateReviewReq dto);

    UpdateReviewRes update(UpdateReviewReq dto);

    void delete(DeleteReviewReq dto);

}
