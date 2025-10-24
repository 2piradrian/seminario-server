package com.group3.events.data.datasource.users_server.responses;

import com.group3.entity.Role;
import com.group3.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthUserRes {

    private final String id;

    private final String email;

    private Status status;

    private Role rol;

}
