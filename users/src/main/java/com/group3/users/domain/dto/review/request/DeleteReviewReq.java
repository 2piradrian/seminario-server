package com.group3.users.domain.dto.review.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeleteReviewReq {

    private final String id;

    private final String token;

    private DeleteReviewReq(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public static DeleteReviewReq create(String id, String token) {
        if (token == null || token.isEmpty()) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (id == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new DeleteReviewReq(id, token);
    }
}
