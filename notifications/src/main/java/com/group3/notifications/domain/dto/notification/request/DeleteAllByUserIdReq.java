package com.group3.notifications.domain.dto.notification.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeleteAllByUserIdReq {

    private final String secret;

    private final String userId;

    private DeleteAllByUserIdReq(String secret, String userId) {
        this.secret = secret;
        this.userId = userId;
    }

    public static DeleteAllByUserIdReq create(String secret, String userId) {
        if (secret == null || userId == null ) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (secret.isEmpty()|| userId.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new DeleteAllByUserIdReq(secret, userId);
    }
}
