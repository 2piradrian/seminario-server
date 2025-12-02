package com.group3.chat.domain.dto.message.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetActiveChatsReq {

    private final String token;
    private final String userId;

    private GetActiveChatsReq(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }

    public static GetActiveChatsReq create(String token, String userId) {
        if (userId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new GetActiveChatsReq(token, userId);
    }
}
