package com.group3.chat.data.repository;

import com.group3.chat.data.datasource.postgres.mapper.ChatMessageEntityMapper;
import com.group3.chat.data.datasource.postgres.model.ChatMessageModel;
import com.group3.chat.data.datasource.postgres.repository.PostgresChatMessageRepositoryI;
import com.group3.chat.domain.repository.ChatMessageRepositoryI;
import com.group3.entity.ChatMessage;
import com.group3.entity.PageContent;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ChatMessageRepository implements ChatMessageRepositoryI {

    private final PostgresChatMessageRepositoryI repository;

    private int normalizePage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }

    @Override
    public PageContent<ChatMessage> getConversation(String user1Id, String user2Id, Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<ChatMessageModel> chatMessageModels = repository.findConversation(
                user1Id,
                user2Id,
                PageRequest.of(pageIndex, size)
        );

        return new PageContent<>(
                chatMessageModels.getContent().stream()
                        .map(ChatMessageEntityMapper::toDomain)
                        .collect(Collectors.toList()),
                chatMessageModels.getNumber() + 1,
                chatMessageModels.hasNext() ? chatMessageModels.getNumber() + 2 : null
        );
    }

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        ChatMessageModel chatMessageModel = ChatMessageEntityMapper.toModel(chatMessage);
        ChatMessageModel saved = this.repository.save(chatMessageModel);
        return ChatMessageEntityMapper.toDomain(saved);
    }

}
