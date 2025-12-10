package com.group3.results.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetFeedMergedByProfileIdPageReq {

    private final Integer page;

    private final Integer size;

    private final String token;

    private final String profileId;

    private GetFeedMergedByProfileIdPageReq(String token, String profileId, Integer page, Integer size ) {
        this.page = page;
        this.size = size;
        this.token = token;
        this.profileId = profileId;
    }

    public static GetFeedMergedByProfileIdPageReq create(String token, String profileId, Integer page, Integer size) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (profileId == null) {
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

        return new GetFeedMergedByProfileIdPageReq(token, profileId, page, size);
    }

}
