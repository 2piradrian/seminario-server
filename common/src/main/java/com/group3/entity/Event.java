package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    private String id;

    private User author;

    private PageProfile pageProfile;

    private String title;

    private String content;

    private String imageId;

    private Date dateInit;

    private Date dateEnd;

    private Integer views;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<String> assists;

    // Domain Variable
    private Integer assistsQuantity;

    // Domain Variable
    private Boolean isAssisting;

    private Status status;

    public void calculateAssistsQuantity(){
        this.assistsQuantity = this.assists.size();
    }

    public void setIsAssisting(String userId){
        this.isAssisting = this.assists.contains(userId);
    }

}
