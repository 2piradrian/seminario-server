package com.group3.users.data.postgres.model;

import com.group3.entity.Role;
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
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private LocalDateTime memberSince;

    private LocalDateTime lastLogin;

    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String portraitImage;

    private String profileImage;

    private String shortDescription;

    private String longDescription;

    private List<String> styles;

    private List<String> instruments;

}
