package com.group3.chat.presentation.service;

import com.group3.chat.domain.dto.message.mapper.ChatMessageMapper;
import com.group3.chat.domain.dto.message.mapper.implementation.GetActiveChatsMapper;
import com.group3.chat.domain.dto.message.mapper.implementation.GetConversationPageMapper;
import com.group3.chat.domain.dto.message.request.GetActiveChatsReq;
import com.group3.chat.domain.dto.message.request.GetConversationPageReq;
import com.group3.chat.domain.dto.message.response.GetActiveChatsRes;
import com.group3.chat.domain.dto.message.response.GetConversationPageRes;
import com.group3.chat.domain.repository.ChatMessageRepositoryI;
import com.group3.chat.domain.repository.UserRepositoryI;
import com.group3.config.PrefixedUUID;
import com.group3.entity.Chat;
import com.group3.entity.ChatMessage;
import com.group3.entity.PageContent;
import com.group3.entity.Status;
import com.group3.entity.User;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        User user1 = userRepository.getById(dto.getUser1Id(), dto.getToken());
        User user2 = userRepository.getById(dto.getUser2Id(), dto.getToken());

        if (user1 == null || user2 == null) {
            return new GetConversationPageMapper().toResponse(new PageContent<>(List.of(), null, null));
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
        return this.chatMessageRepository.save(chatMessage);
    }

    @Override
    public GetActiveChatsRes getActiveChats(GetActiveChatsReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        List<String> activeChatUserIds = this.chatMessageRepository.findActiveChats(user.getId());

        List<Chat> activeChats = activeChatUserIds.stream()
                .flatMap(userId -> {
                    ChatMessage lastMessage = this.chatMessageRepository.findLastMessage(user.getId(), userId);
                    if (lastMessage == null) return Stream.empty();

                    User otherUser = this.userRepository.getById(userId, dto.getToken());
                    if (otherUser == null || otherUser.getStatus() != Status.ACTIVE) {
                        return Stream.empty();
                    }
                    Chat chat = new Chat(
                            userId,
                            lastMessage.getContent(),
                            lastMessage.getCreatedAt(),
                            lastMessage.getSender().getId().equals(user.getId()),
                            otherUser
                    );

                    return Stream.of(chat);
                })
                .collect(Collectors.toList());

        return ChatMessageMapper.getActiveChats().toResponse(activeChats);
    }

}
