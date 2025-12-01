package com.group3.chat.domain.dto.message.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetConversationPageReq {

    private final Integer page;
    private final Integer size;
    private final String token;
    private final String user1Id;
    private final String user2Id;

    private GetConversationPageReq(Integer page, Integer size, String token, String user1Id, String user2Id) {
        this.page = page;
        this.size = size;
        this.token = token;
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }

    public static GetConversationPageReq create(Integer page, Integer size, String token, String user1Id, String user2Id) {
        if (user1Id == null || user2Id == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new GetConversationPageReq(page, size, token, user1Id, user2Id);
    }
}