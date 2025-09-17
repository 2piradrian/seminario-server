package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

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

  private Set<Role> roles;

  private Status status;

}
