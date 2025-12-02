package com.group3.chat.presentation.web_socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.chat.presentation.service.ChatServiceI;
import com.group3.entity.ChatMessage;
import com.group3.entity.User;
import com.group3.chat.domain.repository.UserRepositoryI;
import com.group3.error.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    private final ChatServiceI chatService;
    private final ObjectMapper objectMapper;
    private final UserRepositoryI userRepository;

    public ChatWebSocketHandler(ChatServiceI chatService, ObjectMapper objectMapper, UserRepositoryI userRepository) {
        this.chatService = chatService;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.setTextMessageSizeLimit(512 * 1024);
        session.setBinaryMessageSizeLimit(512 * 1024);

        session.sendMessage(new TextMessage("{\"ping\": true}"));

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            try {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage("{\"ping\": true}"));
                }
            } catch (IOException ignored) {}
        }, 20, 20, TimeUnit.SECONDS);

        User user = authenticate(session);
        if (user != null) {
            sessions.put(user.getId(), session);
        }
        else {
            session.close(CloseStatus.POLICY_VIOLATION.withReason("Unauthorized"));
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        User sender = authenticate(session);

        if (sender == null) {
            session.close(CloseStatus.POLICY_VIOLATION.withReason(ErrorType.UNAUTHORIZED.getMessage()));
            return;
        }

        ChatMessage chatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);
        chatMessage.setSenderId(sender.getId());
        chatMessage.setCreatedAt(LocalDateTime.now());

        ChatMessage savedMessage = this.chatService.save(chatMessage);

        WebSocketSession receiverSession = sessions.get(savedMessage.getReceiverId());
        if (receiverSession != null && receiverSession.isOpen()) {
            receiverSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(savedMessage)));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        User user = authenticate(session);
        if (user != null) {
            sessions.remove(user.getId());
        }
    }

    private User authenticate(WebSocketSession session) {
        try {
            URI uri = session.getUri();
            if (uri == null) {
                return null;
            }

            String query = uri.getQuery();

            if (query == null) {
                return null;
            }

            String[] params = query.split("&");
            String token = null;

            for (String p : params) {
                if (p.startsWith("token=")) {
                    token = p.substring("token=".length());
                    break;
                }
            }


            if (token == null || token.isBlank()) {
                return null;
            }

            String bearerToken = "Bearer " + token;

            User user = this.userRepository.auth(bearerToken);

            return user;

        } catch (Exception e) {
            return null;
        }
    }
}