package com.group3.events.domain.dto.event.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class CancelEventReq {

    private final String eventId;

    private final String token;

    private CancelEventReq(String token, String eventId) {
        this.token = token;
        this.eventId = eventId;
    }

    public static CancelEventReq create(String token, String eventId) {

        if (token == null || token.isEmpty()) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (eventId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new CancelEventReq(token, eventId);
    }
}
