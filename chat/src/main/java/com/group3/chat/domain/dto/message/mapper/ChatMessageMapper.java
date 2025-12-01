package com.group3.chat.domain.dto.message.mapper;

import com.group3.chat.domain.dto.message.mapper.implementation.GetConversationPageMapper;

public class ChatMessageMapper {

    public static GetConversationPageMapper getPage() {
        return new GetConversationPageMapper();
    }
}
