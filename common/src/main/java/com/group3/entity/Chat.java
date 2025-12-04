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
public class Chat {

    private String id;

    private String lastMessage;

    private LocalDateTime createdAt;

    private Boolean isMine;

    private User user;

}
