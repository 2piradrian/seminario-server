package com.group3.notifications.presentation.controller;

import com.group3.notifications.domain.dto.notification.mapper.NotificationMapper;
import com.group3.notifications.domain.dto.notification.request.*;
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

    @PostMapping
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

    @GetMapping("/get-latest-uncheck")
    public ResponseEntity<?> getLatestUncheckNotification(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "secret") String secret,
        @RequestParam(value = "targetId") String targetId,
        @RequestParam(value = "sourceId") String sourceId
    ) {
        GetLatestUncheckNotificationReq dto = NotificationMapper.getLatestUncheck().toRequest(token, secret, targetId, sourceId);
        return ResponseEntity.ok(this.service.getLatestUncheckNotification(dto));
    }

    @PutMapping("/check-invitation/{notificationId}")
    public ResponseEntity<?> checkInvitation(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "secret") String secret,
        @PathVariable(value = "notificationId") String notificationId
    ) {
        CheckInvitationReq dto = NotificationMapper.checkInvitation().toRequest(token, secret, notificationId);
        this.service.checkInvitation(dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<?> markAsRead(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "notificationId") String notificationId
    ) {
        MarkAsReadReq dto = NotificationMapper.markAsRead().toRequest(token, notificationId);
        this.service.markAsRead(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-by-sourceId")
    public ResponseEntity<?> deleteBySourceId(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "secret") String secret,
        @RequestParam(value = "sourceId") String sourceId
    ) {
        DeleteBySourceIdReq dto = NotificationMapper.deleteBySource().toRequest(secret, sourceId ,token);
        this.service.deleteBySourceId(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-all-by-userId")
    public ResponseEntity<?> deleteAllByUserId(
        @RequestParam(value = "secret") String secret,
        @RequestParam(value = "userId") String userId
    ) {
        this.service.deleteAllByUserId(NotificationMapper.deleteAllByUserId().toRequest(secret, userId));
        return ResponseEntity.ok().build();
    }

}
