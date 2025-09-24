package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String name;

    private String surname;

    private String password;

    private String email;

    private LocalDateTime memberSince;

    private LocalDateTime lastLogin;

    private List<Role> roles;

    private Status status;

    private String portraitImage;

    private String profileImage;

    private String shortDescription;

    private String longDescription;

    private List<Style> styles;

    private List<Instrument> instruments;

}
