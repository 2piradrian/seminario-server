package com.group3.posts.data.datasource.postgres.model;

import com.group3.entity.PostTypeEnum;
import com.group3.entity.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class PostModel {

    @Id
    private String id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Integer views;

    @Column(name = "author_id")
    private String authorId;

    @Column(name = "page_id")
    private String pageId;

    @Column(name = "image_id")
    private String imageId;

    @ElementCollection
    @CollectionTable(name = "post_upvoters", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "user_id")
    private List<String> upvoters;

    @ElementCollection
    @CollectionTable(name = "post_downvoters", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "user_id")
    private List<String> downvoters;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private PostTypeEnum postType;

}
