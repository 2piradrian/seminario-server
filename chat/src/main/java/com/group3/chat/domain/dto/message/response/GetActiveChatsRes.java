package com.group3.chat.domain.dto.message.response;

import com.group3.entity.Chat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetActiveChatsRes {

    private final List<Chat> activeChats;

}
