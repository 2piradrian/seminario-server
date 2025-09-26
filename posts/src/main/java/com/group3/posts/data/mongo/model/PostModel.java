package com.group3.posts.data.mongo.model;

import com.group3.entity.Category;
import com.group3.entity.Status;
import jakarta.persistence.Id;
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
@Document(collection = "posts")
public class PostModel {

    @Id
    private String id;

    private String title;

    @Field("content")
    private String content;

    private Integer views;

    private String authorId;

    private Set<String> upvoters;

    private Set<String> downvoters;

    private Category category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Status status;
}
