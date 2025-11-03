package com.group3.users.domain.dto.review.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class GetPageReviewsByReviewedIdReq {

    private final String token;

    private final String userId;

    private final Integer page;

    private final Integer size;

    private GetPageReviewsByReviewedIdReq(String token, String userId, Integer page, Integer size) {
        this.userId = userId;
        this.page = page;
        this.size = size;
        this.token = token;
    }

    public static GetPageReviewsByReviewedIdReq create(String token, String userId, Integer page, Integer size) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (userId == null || userId.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (page == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (page < 0) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (size == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (size < 0) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new GetPageReviewsByReviewedIdReq(token, userId, page, size);
    }
}
