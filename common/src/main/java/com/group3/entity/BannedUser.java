package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannedUser {

    private String id;

    private User bannedBy;

    private String email;

    private String reason;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
