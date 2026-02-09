package com.group3.events.domain.dto.event.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeleteUserDataReq {

    private final String userId;
    private final String secret;

    private DeleteUserDataReq(String userId, String secret) {
        this.userId = userId;
        this.secret = secret;
    }

    public static DeleteUserDataReq create(String userId, String secret) {
        if (userId == null || userId.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        if (secret == null || secret.isEmpty()) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }
        return new DeleteUserDataReq(userId, secret);
    }
}
