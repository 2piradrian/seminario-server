package com.group3.chat.data.datasource.postgres.mapper;

import com.group3.chat.data.datasource.postgres.model.ChatMessageModel;
import com.group3.entity.ChatMessage;
import com.group3.entity.User;

public class ChatMessageEntityMapper {

    public static ChatMessage toDomain(ChatMessageModel chatMessageModel) {
        return new ChatMessage(
                chatMessageModel.getId(),
                User.builder().id(chatMessageModel.getSenderId()).build(),
                User.builder().id(chatMessageModel.getReceiverId()).build(),
                chatMessageModel.getContent(),
                chatMessageModel.getCreatedAt()
        );
    }

    public static ChatMessageModel toModel(ChatMessage chatMessage) {
        return new ChatMessageModel(
                chatMessage.getId(),
                chatMessage.getSender().getId(),
                chatMessage.getReceiver().getId(),
                chatMessage.getContent(),
                chatMessage.getCreatedAt()
        );
    }
}
