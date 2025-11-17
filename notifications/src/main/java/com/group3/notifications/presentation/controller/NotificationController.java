package com.group3.notifications.presentation.controller;

import com.group3.notifications.domain.dto.notification.mapper.NotificationMapper;
import com.group3.notifications.domain.dto.notification.request.CreateNotificationReq;
import com.group3.notifications.domain.dto.notification.request.GetNotificationPageReq;
import com.group3.notifications.presentation.service.NotificationServiceI;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationServiceI service;

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestBody Map<String, Object> payload
    ) {
        CreateNotificationReq dto = NotificationMapper.create().toRequest(payload);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/get-by-target")
    public ResponseEntity<?> getNotificationsByTarget(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        GetNotificationPageReq dto = NotificationMapper.getPage().toRequest(token, payload);
        return ResponseEntity.ok(this.service.getNotificationsByTarget(dto));
    }

}
