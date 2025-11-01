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
public class Post {

    private String id;

    private String title;

    private String content;

    private Integer views;

    private User author;

    private PageProfile pageProfile;

    private String imageId;

    private List<String> upvoters;

    private List<String> downvoters;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Status status;

    // Domain variable
    private Integer upvotersQuantity;

    // Domain variable
    private Integer downvotersQuantity;

    public void setVotersToNull(){
        this.upvoters = null;
        this.downvoters = null;
    }

}
