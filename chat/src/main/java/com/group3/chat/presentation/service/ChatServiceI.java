package com.group3.chat.presentation.service;

import com.group3.chat.domain.dto.message.request.GetActiveChatsReq;
import com.group3.chat.domain.dto.message.request.GetConversationPageReq;
import com.group3.chat.domain.dto.message.response.GetConversationPageRes;
import com.group3.entity.Chat;
import com.group3.entity.ChatMessage;

import java.util.List;

public interface ChatServiceI {

    GetConversationPageRes getConversation(GetConversationPageReq dto);

    ChatMessage save(ChatMessage chatMessage);

    List<Chat> getActiveChats(GetActiveChatsReq dto);

}
