package com.group3.page_profiles.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeleteUserPagesReq {
    
    private final String userId;
    private final String secret;

    private DeleteUserPagesReq(String userId, String secret) {
        this.userId = userId;
        this.secret = secret;
    }

    public static DeleteUserPagesReq create(String userId, String secret) {
        if (userId == null || userId.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        if (secret == null || secret.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new DeleteUserPagesReq(userId, secret);
    }
}
