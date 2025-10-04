package com.group3.users.data.datasource.postgres.model;

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
    private String id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    @Enumerated(EnumType.STRING)
    private Status status;

}
