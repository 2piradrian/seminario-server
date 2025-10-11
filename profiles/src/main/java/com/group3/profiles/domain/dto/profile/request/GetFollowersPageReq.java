package com.group3.profiles.domain.dto.profile.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetFollowersPageReq {

    private final String followerId;

    private final Integer page;

    private final Integer size;

    public GetFollowersPageReq(String followerId, Integer page, Integer size) {
        this.followerId = followerId;
        this.page = page;
        this.size = size;
    }

    public static GetFollowersPageReq create(String followerId, Integer page, Integer size) {

        if (followerId == null || followerId.isEmpty()) {
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

        return new GetFollowersPageReq(followerId, page, size);
    }
}

