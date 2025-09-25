package com.group3.profiles.data.postgres.model;

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
@Table(name = "users")
public class UserProfileModel {

    @Id
    private String id;

    private String email;

    private String name;

    private String surname;

    private LocalDateTime memberSince;

    private LocalDateTime lastLogin;

    @Column(columnDefinition = "TEXT")
    private String portraitImage;

    @Column(columnDefinition = "TEXT")
    private String profileImage;

    private String shortDescription;

    private String longDescription;

    private List<String> styles;

    private List<String> instruments;

}
