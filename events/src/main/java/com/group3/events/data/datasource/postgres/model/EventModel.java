package com.group3.events.data.datasource.postgres.model;

import com.group3.entity.PageProfile;
import com.group3.entity.Status;
import com.group3.entity.UserProfile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class EventModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String authorId;

    private String pageId;

    private String title;

    private String imageId;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Date dateInit;

    private Date dateEnd;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer views;

    @ElementCollection
    @CollectionTable(name = "event_assist", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "profile_id")
    private List<String> assist;

    @Enumerated(EnumType.STRING)
    private Status status;

}
