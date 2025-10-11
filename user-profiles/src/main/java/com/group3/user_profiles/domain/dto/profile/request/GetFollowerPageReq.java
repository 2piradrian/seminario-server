package com.group3.user_profiles.domain.dto.profile.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetFollowerPageReq {

    private final String followerId;

    private final Integer page;

    private final Integer size;

    public GetFollowerPageReq(String followerId, Integer page, Integer size) {
        this.followerId = followerId;
        this.page = page;
        this.size = size;
    }

    public static GetFollowerPageReq create(String followerId, Integer page, Integer size) {

        if (followerId == null) {
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

        return new GetFollowerPageReq(followerId, page, size);
    }
}
