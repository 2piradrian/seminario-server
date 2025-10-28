package com.group3.events.domain.dto.event.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class ToggleAsistReq {

    private final String token;

    private final String eventId;

    private ToggleAsistReq(String token, String eventId) {
        this.token = token;
        this.eventId = eventId;
    }

    public static ToggleAsistReq create(String token, String eventId) {
        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }
        if (eventId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new ToggleAsistReq(token, eventId);
    }

    public String getToken() {
        return token;
    }

    public String getEventId() {
        return eventId;
    }
}
