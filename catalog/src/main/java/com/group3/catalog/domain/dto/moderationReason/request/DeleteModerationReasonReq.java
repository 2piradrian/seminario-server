package com.group3.catalog.domain.dto.moderationReason.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeleteModerationReasonReq {

    private final String token;
    private final String id;

    private DeleteModerationReasonReq(String token, String id) {
        this.token = token;
        this.id = id;
    }

    public static DeleteModerationReasonReq create(String token, String id) {
        if (token == null || token.isEmpty() || id == null || id.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new DeleteModerationReasonReq(token, id);
    }
}
