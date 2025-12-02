package com.group3.chat.domain.dto.message.mapper.implementation;

import com.group3.chat.domain.dto.message.request.GetActiveChatsReq;
import com.group3.chat.domain.dto.message.response.GetActiveChatsRes;
import com.group3.entity.Chat;

import java.util.List;

public class GetActiveChatsMapper {
    public GetActiveChatsReq toRequest(String token) {
        return GetActiveChatsReq.create(
                token
        );
    }

    public GetActiveChatsRes toResponse(List<Chat> activeChats) {
        return new GetActiveChatsRes(
                activeChats
        );
    }
}
