package com.group3.users.domain.dto.user.response;

import com.group3.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class RegisterUserRes {

    private final String userId;

    private final String name;

    private final String surname;

    private final String email;

    private final List<Role> roles;

    private final LocalDateTime memberSince;

    private final LocalDateTime lastLogin;

}
