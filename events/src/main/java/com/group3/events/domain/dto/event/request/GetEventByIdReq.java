package com.group3.events.domain.dto.event.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetEventByIdReq {

    private final String eventId;

    private final String token;

    private GetEventByIdReq(String eventId, String token) {
        this.eventId = eventId;
        this.token = token;
    }

    public static GetEventByIdReq create(String eventId, String token) {

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (eventId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetEventByIdReq(eventId, token);
    }

}
