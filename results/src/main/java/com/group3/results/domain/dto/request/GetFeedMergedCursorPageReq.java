package com.group3.results.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetFeedMergedPageReq {

    private final LocalDateTime cursor;

    private final Integer size;

    private final String token;

    private final String profileId;

    private GetFeedMergedPageReq(String token, String profileId, LocalDateTime cursor, Integer size ) {
        this.cursor = cursor;
        this.size = size;
        this.token = token;
        this.profileId = profileId;
    }

    public static GetFeedMergedPageReq create(String token, String profileId, LocalDateTime cursor, Integer size) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (profileId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (cursor != null && cursor.isAfter(LocalDateTime.now())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (size == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (size < 0) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new GetFeedMergedPageReq(token, profileId, cursor, size);
    }

}
