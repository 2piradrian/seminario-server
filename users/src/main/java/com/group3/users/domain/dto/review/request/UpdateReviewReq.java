package com.group3.users.domain.dto.review.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.domain.validator.RegexValidators;
import lombok.Getter;

@Getter
public class UpdateReviewReq {

    private final String id;

    private final String review;

    private final Float rating;

    private final String token;

    private UpdateReviewReq(String id, String review, Float rating, String token) {
        this.id = id;
        this.review = review;
        this.rating = rating;
        this.token = token;
    }

    public static UpdateReviewReq create(String id, String review, Float rating, String token) {
        if (token == null || token.isEmpty()) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (id == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (review != null && (review.isEmpty() || !review.matches(RegexValidators.COMMENT_CONTENT.getRegex()))) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (rating != null && (rating < 0 || rating > 5)) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new UpdateReviewReq(id, review, rating, token);
    }
}
