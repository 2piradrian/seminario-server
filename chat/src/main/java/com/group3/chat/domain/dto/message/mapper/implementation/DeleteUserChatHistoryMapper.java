package com.group3.chat.domain.dto.message.mapper.implementation;

import com.group3.chat.domain.dto.message.request.DeleteUserChatHistoryReq;

public class DeleteUserChatHistoryMapper {

    public DeleteUserChatHistoryReq toRequest(String userId, String secret) {
        return DeleteUserChatHistoryReq.create(userId, secret);
    }

}
