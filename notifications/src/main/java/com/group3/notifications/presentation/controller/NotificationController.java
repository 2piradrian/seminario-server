package com.group3.notifications.presentation.controller;

import com.group3.notifications.domain.dto.notification.mapper.NotificationMapper;
import com.group3.notifications.domain.dto.notification.request.CreateNotificationReq;
import com.group3.notifications.domain.dto.notification.request.GetNotificationPageReq;
import com.group3.notifications.presentation.service.NotificationServiceI;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationServiceI service;

    @PostMapping()
    public ResponseEntity<?> create(
            @RequestBody Map<String, Object> payload
    ) {
        CreateNotificationReq dto = NotificationMapper.create().toRequest(payload);
        this.service.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-by-target")
    public ResponseEntity<?> getNotificationsByTarget(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "targetId") String targetId
    ) {
        GetNotificationPageReq dto = NotificationMapper.getPage().toRequest(token, page, size, targetId);
        return ResponseEntity.ok(this.service.getNotificationsByTarget(dto));
    }

}