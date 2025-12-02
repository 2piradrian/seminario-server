package com.group3.chat.domain.dto.message.mapper;

import com.group3.chat.domain.dto.message.mapper.implementation.GetActiveChatsMapper;
import com.group3.chat.domain.dto.message.mapper.implementation.GetConversationPageMapper;

public class ChatMessageMapper {

    public static GetConversationPageMapper getPage() {
        return new GetConversationPageMapper();
    }

    public static GetActiveChatsMapper getActiveChats() {
        return new GetActiveChatsMapper();
    }

}
