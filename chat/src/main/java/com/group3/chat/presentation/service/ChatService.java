package com.group3.chat.presentation.service;

import com.group3.chat.domain.dto.message.mapper.implementation.GetActiveChatsMapper;
import com.group3.chat.domain.dto.message.mapper.implementation.GetConversationPageMapper;
import com.group3.chat.domain.dto.message.request.GetActiveChatsReq;
import com.group3.chat.domain.dto.message.request.GetConversationPageReq;
import com.group3.chat.domain.dto.message.response.GetActiveChatsRes;
import com.group3.chat.domain.dto.message.response.GetConversationPageRes;
import com.group3.chat.domain.repository.ChatMessageRepositoryI;
import com.group3.chat.domain.repository.UserRepositoryI;
import com.group3.config.PrefixedUUID;
import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ChatService implements ChatServiceI {

    private final UserRepositoryI userRepository;
    private final ChatMessageRepositoryI chatMessageRepository;

    @Override
    public GetConversationPageRes getConversation(GetConversationPageReq dto) {
        User user = userRepository.auth(dto.getToken());

        if (user == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (!user.getId().equals(dto.getUser1Id()) && !user.getId().equals(dto.getUser2Id())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        PageContent<ChatMessage> messages = this.chatMessageRepository.getConversation(
                dto.getUser1Id(),
                dto.getUser2Id(),
                dto.getPage(),
                dto.getSize()
        );

        return new GetConversationPageMapper().toResponse(messages);
    }

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        String uuid = PrefixedUUID.generate(PrefixedUUID.EntityType.CHAT_MESSAGE).toString();
        chatMessage.setId(uuid);
        return chatMessageRepository.save(chatMessage);
    }

    @Override
    public List<Chat> getActiveChats(GetActiveChatsReq dto) {
        User user = userRepository.auth(dto.getToken());

        if (user == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        return chatMessageRepository.findActiveChats(user.getId());
    }

}