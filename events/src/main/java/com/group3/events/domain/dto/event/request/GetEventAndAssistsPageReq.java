package com.group3.events.domain.dto.event.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetEventAndAssistsPageReq {

    private final String token;

    private final String userId;

    private final Integer page;

    private final Integer size;

    private GetEventAndAssistsPageReq(String token, String userId, Integer page, Integer size) {
        this.page = page;
        this.size = size;
        this.token = token;
        this.userId = userId;
    }

    public static GetEventAndAssistsPageReq create(String token, String userId, Integer page, Integer size) {

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

        return new GetEventAndAssistsPageReq(token,userId, page, size);
    }

}
