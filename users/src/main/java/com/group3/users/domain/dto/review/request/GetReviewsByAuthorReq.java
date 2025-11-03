package com.group3.users.domain.dto.review.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetReviewsByAuthorReq {

    private final String token;

    private final Integer page;

    private final Integer size;

    private GetReviewsByAuthorReq(String token, Integer page, Integer size) {
        this.page = page;
        this.size = size;
        this.token = token;
    }

    public static GetReviewsByAuthorReq create(String token, Integer page, Integer size) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
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

        return new GetReviewsByAuthorReq(token, page, size);
    }

}
