package com.group3.users.data.datasource.postgres.model;

import com.group3.entity.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class ReviewModel {

    @Id
    private String id;

    @Column(name = "reviewed_id", nullable = false)
    private String reviewedId;

    @Column(name = "reviewer_user_id", nullable = false)
    private String reviewerUserId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String review;

    @Column(nullable = false)
    private Float rating;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

}
