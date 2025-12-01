package com.group3.chat.domain.dto.message.mapper.implementation;

import com.group3.entity.ChatMessage;
import com.group3.entity.PageContent;
import com.group3.chat.domain.dto.message.request.GetConversationPageReq;
import com.group3.chat.domain.dto.message.response.GetConversationPageRes;

public class GetConversationPageMapper {

    public GetConversationPageReq toRequest(String token, Integer page, Integer size, String user1Id, String user2Id) {
        return GetConversationPageReq.create(
                page,
                size,
                token,
                user1Id,
                user2Id
        );
    }

    public GetConversationPageRes toResponse(PageContent<ChatMessage> messages) {
        return new GetConversationPageRes(
                messages.getContent(),
                messages.getNextPage()
        );
    }
}