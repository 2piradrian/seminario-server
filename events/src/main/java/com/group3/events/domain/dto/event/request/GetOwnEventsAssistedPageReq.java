package com.group3.events.domain.dto.event.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetOwnEventsAssistedPageReq {

    private final String token;

    private final Integer page;

    private final Integer size;

    private GetOwnEventsAssistedPageReq(String token, Integer page, Integer size) {
        this.token = token;
        this.page = page;
        this.size = size;
    }

    public static GetOwnEventsAssistedPageReq create(String token, Integer page, Integer size) {
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

        return new GetOwnEventsAssistedPageReq(token, page, size);
    }

}
