package com.group3.events.domain.dto.event.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetEventByProfileIdPageReq {

    private final String token;

    private final String profileId;

    private final Integer page;

    private final Integer size;

    private GetEventByProfileIdPageReq(String token, String profileId, Integer page, Integer size) {
        this.page = page;
        this.size = size;
        this.token = token;
        this.profileId = profileId;
    }

    public static GetEventByProfileIdPageReq create(String token, String profileId, Integer page, Integer size) {

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

        return new GetEventByProfileIdPageReq(token, profileId, page, size);
    }
}
