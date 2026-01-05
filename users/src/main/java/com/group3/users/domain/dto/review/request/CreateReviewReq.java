package com.group3.users.domain.dto.review.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.domain.validator.RegexValidators;
import lombok.Getter;

@Getter
public class CreateReviewReq {

    private final String reviewedUserId;

    private final String review;

    private final Float rating;

    private final String token;

    private CreateReviewReq(String reviewedUserId, String review, Float rating, String token) {
        this.reviewedUserId = reviewedUserId;
        this.review = review;
        this.rating = rating;
        this.token = token;
    }

    public static CreateReviewReq create(String reviewedUserId, String review, Float rating, String token) {

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (reviewedUserId == null || review == null || rating == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (review.isEmpty() || !review.matches(RegexValidators.COMMENT_CONTENT.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (rating < 0 || rating > 5) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new CreateReviewReq(reviewedUserId, review, rating, token);
    }

}
