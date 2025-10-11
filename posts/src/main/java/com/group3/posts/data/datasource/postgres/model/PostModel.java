package com.group3.posts.data.datasource.postgres.model;

import com.group3.entity.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Integer views;

    private String authorId;

    private String pageId;

    private String imageId;

    @ElementCollection
    @CollectionTable(name = "post_upvoters", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "user_id")
    private List<String> upvoters;

    @ElementCollection
    @CollectionTable(name = "post_downvoters", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "user_id")
    private List<String> downvoters;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

}
