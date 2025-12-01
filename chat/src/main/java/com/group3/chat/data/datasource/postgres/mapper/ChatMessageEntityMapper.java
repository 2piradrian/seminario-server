package com.group3.chat.data.datasource.postgres.mapper;

import com.group3.chat.data.datasource.postgres.model.ChatMessageModel;
import com.group3.entity.ChatMessage;

public class ChatMessageEntityMapper {

    public static ChatMessage toDomain(ChatMessageModel chatMessageModel) {
        return new ChatMessage(
                chatMessageModel.getId(),
                chatMessageModel.getSenderId(),
                chatMessageModel.getReceiverId(),
                chatMessageModel.getContent(),
                chatMessageModel.getCreatedAt()
        );
    }

    public static ChatMessageModel toModel(ChatMessage chatMessage) {
        return new ChatMessageModel(
                chatMessage.getId(),
                chatMessage.getSenderId(),
                chatMessage.getReceiverId(),
                chatMessage.getContent(),
                chatMessage.getCreatedAt()
        );
    }
}
