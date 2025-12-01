package com.group3.chat.domain.dto.message.response;

import com.group3.entity.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetConversationPageRes {

    private final List<ChatMessage> messages;
    private final Integer nextPage;

}
