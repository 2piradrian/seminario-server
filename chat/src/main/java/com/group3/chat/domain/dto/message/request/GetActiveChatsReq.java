package com.group3.chat.domain.dto.message.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetActiveChatsReq {

    private final String token;

    private GetActiveChatsReq(String token) {
        this.token = token;
    }

    public static GetActiveChatsReq create(String token) {
        if (token == null || token.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new GetActiveChatsReq(token);
    }

}
