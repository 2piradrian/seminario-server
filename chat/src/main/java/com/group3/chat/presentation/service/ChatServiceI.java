package com.group3.chat.presentation.service;

import com.group3.chat.domain.dto.message.request.DeleteUserChatHistoryReq;
import com.group3.chat.domain.dto.message.request.GetActiveChatsReq;
import com.group3.chat.domain.dto.message.request.GetConversationPageReq;
import com.group3.chat.domain.dto.message.response.GetActiveChatsRes;
import com.group3.chat.domain.dto.message.response.GetConversationPageRes;
import com.group3.entity.ChatMessage;

public interface ChatServiceI {

    GetConversationPageRes getConversation(GetConversationPageReq dto);

    GetActiveChatsRes getActiveChats(GetActiveChatsReq dto);

    ChatMessage save(ChatMessage chatMessage);

    void deleteUserHistory(DeleteUserChatHistoryReq dto);

}
