package com.group3.posts.data.datasource.postgres.model;

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
@Table(name = "comments")
public class CommentModel {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private String Id;

    @Column(name = "author_id", nullable = false)
    private String authorId;

    @Column(name = "post_id", nullable = false)
    private String postId;

    @Column(name = "page_id")
    private String pageId;

    @ManyToOne
    private CommentModel replyTo;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ElementCollection
    @CollectionTable(name = "comment_upvoters", joinColumns = @JoinColumn(name = "comment_id"))
    @Column(name = "user_id")
    private List<String> upvoters;

    @ElementCollection
    @CollectionTable(name = "comment_downvoters", joinColumns = @JoinColumn(name = "comment_id"))
    @Column(name = "user_id")
    private List<String> downvoters;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private  LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

}
