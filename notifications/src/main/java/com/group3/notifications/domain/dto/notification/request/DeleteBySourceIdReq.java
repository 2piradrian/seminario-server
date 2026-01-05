package com.group3.notifications.domain.dto.notification.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeleteBySourceIdReq {

    private final String secret;

    private final String token;

    private final String sourceId;

    private DeleteBySourceIdReq(String secret, String sourceId, String token) {
        this.secret = secret;
        this.sourceId = sourceId;
        this.token = token;
    }

    public static DeleteBySourceIdReq create(String secret, String sourceId, String token) {
        if (secret == null || sourceId == null || token == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (secret.isEmpty()|| sourceId.isEmpty() || token.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new DeleteBySourceIdReq(secret, sourceId, token);
    }
}
