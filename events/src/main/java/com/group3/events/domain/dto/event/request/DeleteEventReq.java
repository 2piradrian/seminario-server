package com.group3.events.domain.dto.event.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeleteEventReq {

    private final String eventId;
    private final String token;
    private final String reasonId;

    private DeleteEventReq(String token, String eventId, String reasonId) {
        this.token = token;
        this.eventId = eventId;
        this.reasonId = reasonId;
    }

    public static DeleteEventReq create(String token, String eventId, String reasonId) {
        if (token == null || token.isEmpty()) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }
        if (eventId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new DeleteEventReq(token, eventId, reasonId);
    }
}
