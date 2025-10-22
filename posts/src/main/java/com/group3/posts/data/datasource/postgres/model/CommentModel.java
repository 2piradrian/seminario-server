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

    private  String authorId;

    @Column(name = "post_id", nullable = false)
    private String postId;

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

    private LocalDateTime createdAt;

    private  LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

}
