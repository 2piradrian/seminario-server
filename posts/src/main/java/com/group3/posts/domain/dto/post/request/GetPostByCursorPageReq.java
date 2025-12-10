package com.group3.posts.domain.dto.post.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetPostByCursorPageReq {

    private final String token;

    private final String profileId;

    private final LocalDateTime cursor;

    private final Integer size;

    private GetPostByCursorPageReq(String token, String profileId, LocalDateTime cursor, Integer size) {
        this.cursor = cursor;
        this.size = size;
        this.token = token;
        this.profileId = profileId;
    }

    public static GetPostByCursorPageReq create(String token, String profileId, LocalDateTime cursor, Integer size) {

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

        return new GetPostByCursorPageReq(token, profileId, cursor, size);
    }
}
