package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    private String id;

    private UserProfile author;

    private PageProfile pageProfile;

    private String title;

    private String content;

    private String imageId;

    private Date dateInit;

    private Date dateEnd;

    private Integer views;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<String> assist;

    private Status status;

}
