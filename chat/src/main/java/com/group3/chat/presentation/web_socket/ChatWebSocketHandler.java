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
        log.info("New WS connection -> sessionId={}, uri={}", session.getId(), session.getUri());

        User user = authenticate(session);

        if (user != null) {
            sessions.put(user.getId(), session);
            log.info("User {} authenticated successfully. Session {}", user.getId(), session.getId());
        } else {
            log.warn("Authentication FAILED for session {}. Closing...", session.getId());
            session.close(CloseStatus.POLICY_VIOLATION.withReason(ErrorType.UNAUTHORIZED.getMessage()));
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("Received message on session {} -> {}", session.getId(), message.getPayload());

        User sender = authenticate(session);

        if (sender == null) {
            log.warn("Unauthorized text message attempt. Closing session {}", session.getId());
            session.close(CloseStatus.POLICY_VIOLATION.withReason(ErrorType.UNAUTHORIZED.getMessage()));
            return;
        }

        ChatMessage chatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);
        chatMessage.setSenderId(sender.getId());
        chatMessage.setCreatedAt(LocalDateTime.now());

        ChatMessage savedMessage = this.chatService.save(chatMessage);

        log.info("Message stored. sender={}, receiver={}, id={}",
                savedMessage.getSenderId(),
                savedMessage.getReceiverId(),
                savedMessage.getId()
        );

        WebSocketSession receiverSession = sessions.get(savedMessage.getReceiverId());
        if (receiverSession != null && receiverSession.isOpen()) {
            log.info("Delivering message {} to receiver {}", savedMessage.getId(), savedMessage.getReceiverId());
            receiverSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(savedMessage)));
        } else {
            log.info("Receiver {} is offline. Message {} will be stored only.", savedMessage.getReceiverId(), savedMessage.getId());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("WS Closed -> sessionId={}, status={}", session.getId(), status);

        User user = authenticate(session);
        if (user != null) {
            sessions.remove(user.getId());
            log.info("User {} removed from active session list", user.getId());
        }
    }

    private User authenticate(WebSocketSession session) {
        try {
            URI uri = session.getUri();
            if (uri == null) {
                log.error("Session {} has no URI", session.getId());
                return null;
            }

            String query = uri.getQuery();
            log.info("Session {} query raw: {}", session.getId(), query);

            if (query == null) {
                log.warn("No query params found in session {}", session.getId());
                return null;
            }

            // Parseo seguro del token
            String[] params = query.split("&");
            String token = null;

            for (String p : params) {
                if (p.startsWith("token=")) {
                    token = p.substring("token=".length());
                    break;
                }
            }

            log.info("Parsed token for session {} -> {}", session.getId(), token);

            if (token == null || token.isBlank()) {
                log.warn("Token missing or blank in session {}", session.getId());
                return null;
            }

            String bearerToken = "Bearer " + token;

            User user = this.userRepository.auth(bearerToken);

            if (user == null) {
                log.warn("Repository denied token for session {}", session.getId());
            } else {
                log.info("Repository authenticated user {} for session {}", user.getId(), session.getId());
            }

            return user;

        } catch (Exception e) {
            log.error("Error authenticating session {} -> {}", session.getId(), e.getMessage(), e);
            return null;
        }
    }
}