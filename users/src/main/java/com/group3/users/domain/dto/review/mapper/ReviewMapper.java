package com.group3.users.domain.dto.review.mapper;

import com.group3.users.domain.dto.review.mapper.implementation.*;

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

    public static GetPageByReviewedMapper getReviewsByReviewed() {
        return new GetPageByReviewedMapper();
    }

}
