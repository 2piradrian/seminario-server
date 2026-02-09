package com.group3.chat.domain.dto.message.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeleteUserChatHistoryReq {

    private final String userId;
    private final String secret;

    private DeleteUserChatHistoryReq(String userId, String secret) {
        this.userId = userId;
        this.secret = secret;
    }

    public static DeleteUserChatHistoryReq create(String userId, String secret) {
        if (userId == null || secret == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new DeleteUserChatHistoryReq(userId, secret);
    }
}
