package com.group3.events.domain.dto.event.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetOnlyPageEventPageReq {

    private final String token;

    private final String secret;

    private final Integer page;

    private final Integer size;

    private GetOnlyPageEventPageReq(String token, String secret, Integer page, Integer size) {
        this.page = page;
        this.size = size;
        this.token = token;
        this.secret = secret;
    }

    public static GetOnlyPageEventPageReq create(String token, String secret, Integer page, Integer size) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (secret == null || secret.isBlank()) {
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

        return new GetOnlyPageEventPageReq(token, secret, page, size);
    }
}
