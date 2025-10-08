package com.group3.profiles.data.datasource.postgres.model;

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
    @Column(unique = true)
    private String id;

    @Column(unique = true)
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

    private List<String> styles;

    private List<String> instruments;

}
