package com.grupo3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenClaims {

  private String id;

  private String email;

  private Set<Role> roles;

}
