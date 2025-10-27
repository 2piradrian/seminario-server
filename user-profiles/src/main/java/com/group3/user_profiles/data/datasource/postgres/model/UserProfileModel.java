package com.group3.user_profiles.data.datasource.postgres.model;

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
@Table(name = "profiles")
public class UserProfileModel {

    @Id
    @Column(unique = true, nullable = false)
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    private String name;
    private String surname;
    private LocalDateTime memberSince;

    @Column(columnDefinition = "TEXT")
    private String portraitImage;

    @Column(columnDefinition = "TEXT")
    private String profileImage;

    private String shortDescription;

    private String longDescription;

    @ElementCollection
    @CollectionTable(name = "user_styles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "style")
    private List<String> styles;

    @ElementCollection
    @CollectionTable(name = "user_instruments", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "instrument")
    private List<String> instruments;

    @ElementCollection
    @CollectionTable(name = "user_following", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "following_id")
    private List<String> following;

    @OneToMany(mappedBy = "reviewedUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReviewModel> receivedReviews;

    @OneToMany(mappedBy = "reviewerUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReviewModel> writtenReviews;

    @Enumerated(EnumType.STRING)
    private Status status;

}
