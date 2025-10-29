package com.group3.users.domain.dto.follow.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetFollowingPageReq {

    private final String userId;

    private final Integer page;

    private final Integer size;

    private final String token;

    private GetFollowingPageReq(String userId, Integer page, Integer size, String token) {
        this.userId = userId;
        this.page = page;
        this.size = size;
        this.token = token;
    }

    public static GetFollowingPageReq create(String userId, Integer page, Integer size, String token) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (userId == null) {
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

        return new GetFollowingPageReq(userId, page, size, token);
    }

}
