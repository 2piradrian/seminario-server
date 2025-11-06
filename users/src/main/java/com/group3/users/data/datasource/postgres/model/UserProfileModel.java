package com.group3.users.data.datasource.postgres.model;

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

}
