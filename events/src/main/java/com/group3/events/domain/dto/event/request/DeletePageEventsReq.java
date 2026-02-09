package com.group3.events.domain.dto.event.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeletePageEventsReq {

    private final String pageId;
    private final String secret;

    private DeletePageEventsReq(String pageId, String secret) {
        this.pageId = pageId;
        this.secret = secret;
    }

    public static DeletePageEventsReq create(String pageId, String secret) {
        if (pageId == null || pageId.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        if (secret == null || secret.isEmpty()) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }
        return new DeletePageEventsReq(pageId, secret);
    }
}
