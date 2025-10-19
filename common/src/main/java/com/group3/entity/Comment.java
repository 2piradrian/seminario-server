package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private String id;

    private UserProfile author;

    private String postId;

    private Comment replyTo;

    private String content;

    private List<String> upvoters;

    private List<String> downvoters;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private PageProfile pageProfile;

    private Status status;

    // Domain variable
    private Integer upvotersQuantity;

    // Domain variable
    private Integer downvotersQuantity;

}
