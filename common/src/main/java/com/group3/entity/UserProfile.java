package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

  private String id;

  private String portraitImage;

  private String profileImage;

  private String shortDescription;

  private String longDescription;

  private Set<Style> styles;

  private Set<Instrument> instruments;

}
