package com.group3.posts.data.datasource.postgres.model;

import com.group3.entity.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class CommentModel {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private String Id;

    private  String authorId;

    @Column(name = "post_id", nullable = false)
    private String postId;

    private String pageId;

    @ManyToOne
    private CommentModel replyTo;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Set<String> upvoters;

    private  Set<String> downvoters;

    private LocalDateTime createdAt;

    private  LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

}
