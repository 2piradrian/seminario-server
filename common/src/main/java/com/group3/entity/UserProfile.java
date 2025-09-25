package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    private String id;

    private String email;

    private String name;

    private String surname;

    private LocalDateTime memberSince;

    private LocalDateTime lastLogin;

    private String portraitImage;

    private String profileImage;

    private String shortDescription;

    private String longDescription;

    private List<Style> styles;

    private List<Instrument> instruments;

}
