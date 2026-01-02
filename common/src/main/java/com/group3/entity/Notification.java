package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    private String id;

    private String targetId;

    private String sourceId;

    private User carriedOutBy;

    private NotificationContent content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean isRead;

}
