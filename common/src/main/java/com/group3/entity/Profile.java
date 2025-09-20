package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

  private String portaitImage;

  private String profileImage;

  private String shortDescription;

  private String longDescription;

  private List<Style> styles;

  private List<Instrument> instruments;

  private LocalDateTime lastLogin;

  private Set<Role> roles;

  private Status status;

}
