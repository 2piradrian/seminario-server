package com.group3.profiles.domain.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthUserRes {

    private final String id;

    private final String email;

}
