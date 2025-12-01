package com.group3.chat.domain.repository;

import com.group3.entity.ChatMessage;
import com.group3.entity.PageContent;

import java.util.List;

public interface ChatMessageRepositoryI {

    PageContent<ChatMessage> getConversation(String user1Id, String user2Id, Integer page, Integer size);

    ChatMessage save(ChatMessage chatMessage);

    List<String> findActiveChats(String userId);

}