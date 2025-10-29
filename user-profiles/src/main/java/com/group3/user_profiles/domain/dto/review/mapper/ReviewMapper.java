package com.group3.user_profiles.domain.dto.review.mapper;

import com.group3.user_profiles.domain.dto.review.mapper.implementation.CreateMapper;
import com.group3.user_profiles.domain.dto.review.mapper.implementation.DeleteMapper;
import com.group3.user_profiles.domain.dto.review.mapper.implementation.GetReviewsByAuthorMapper;
import com.group3.user_profiles.domain.dto.review.mapper.implementation.UpdateMapper;

public class ReviewMapper {

    public static CreateMapper create() {
        return new CreateMapper();
    }

    public static UpdateMapper update() {
        return new UpdateMapper();
    }

    public static DeleteMapper delete() {
        return new DeleteMapper();
    }

    public static GetReviewsByAuthorMapper getReviewsByAuthor() {
        return new GetReviewsByAuthorMapper();
    }

}
