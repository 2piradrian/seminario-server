package com.group3.posts.data.mongo.model;

import com.group3.entity.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comments")
public class CommentModel {

    @Id
    private String id;

    private  String authorId;

    @Field("post_id")
    private String postId;

    private String replyToId; // cambia por mongo

    private String content;

    private Set<String> upvoters;

    private  Set<String> downvoters;

    private LocalDateTime createdAt;

    private  LocalDateTime updatedAt;

    private Status status;

}
