package com.group3.users.data.datasource.postgres.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "banned_users")
public class BannedUserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String bannedBy;

    @Column(unique = true)
    private String email;

    private String reasonId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
