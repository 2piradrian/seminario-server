package com.group3.user_profiles.data.datasource.postgres.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class ReviewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "reviewed_id", nullable = false)
    private String reviewedId;

    @Column(name = "reviewer_user_id", nullable = false)
    private String reviewerUserId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String review;

    @Column(nullable = false)
    private Float rating;

}
