package com.group3.events.domain.dto.event.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetAssistantsByEventIdReq {

    private final String token;

    private final String eventId;

    private final Integer page;

    private final Integer size;

    private GetAssistantsByEventIdReq(String token, String eventId, Integer page, Integer size) {
        this.token = token;
        this.eventId = eventId;
        this.page = page;
        this.size = size;
    }

    public static GetAssistantsByEventIdReq create(String token, String eventId, Integer page, Integer size) {
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

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }
        if (eventId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetAssistantsByEventIdReq(token, eventId, page, size);
    }
}
