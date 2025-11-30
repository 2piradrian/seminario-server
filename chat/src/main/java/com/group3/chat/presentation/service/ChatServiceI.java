package com.group3.chat.presentation.service;

import com.group3.chat.domain.dto.message.request.GetConversationPageReq;
import com.group3.chat.domain.dto.message.response.GetConversationPageRes;

public interface ChatServiceI {

    GetConversationPageRes getConversation(GetConversationPageReq dto);

}
