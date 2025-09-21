package com.group3.users.domain.dto.user.response;

import com.group3.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@AllArgsConstructor
public class GetUserByIdRes {

    private final String id;

    private final String name;

    private final String surname;

    private final String email;

    private final Set<Role> roles;

    private LocalDateTime memberSince;

    private LocalDateTime lastLogin;

}
