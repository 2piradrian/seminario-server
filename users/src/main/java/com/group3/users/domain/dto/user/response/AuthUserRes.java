package com.group3.users.domain.dto.user.response;

import com.group3.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class AuthUserRes {

    private final String id;

    private final String email;

    private final List<Role> roles;

}
