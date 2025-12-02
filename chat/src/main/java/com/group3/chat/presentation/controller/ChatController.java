package com.group3.chat.presentation.controller;

import com.group3.chat.domain.dto.message.mapper.ChatMessageMapper;
import com.group3.chat.domain.dto.message.request.GetActiveChatsReq;
import com.group3.chat.domain.dto.message.request.GetConversationPageReq;
import com.group3.chat.presentation.service.ChatServiceI;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatServiceI service;

    @GetMapping("/get-conversation")
    public ResponseEntity<?> getConversation(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "user1Id") String user1Id,
            @RequestParam(value = "user2Id") String user2Id
    ) {
        GetConversationPageReq dto = ChatMessageMapper.getPage().toRequest(token, page, size, user1Id, user2Id);
        return ResponseEntity.ok(this.service.getConversation(dto));
    }

    @GetMapping("/active-chats")
    public ResponseEntity<?> getActiveChats(
            @RequestHeader(value = "Authorization") String token
    ) {
        GetActiveChatsReq dto = GetActiveChatsReq.create(token);
        return ResponseEntity.ok(service.getActiveChats(dto));
    }

}