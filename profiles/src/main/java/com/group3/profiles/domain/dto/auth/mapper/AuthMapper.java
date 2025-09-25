package com.group3.profiles.domain.dto.auth.mapper;

import com.group3.profiles.domain.dto.auth.mapper.implementation.*;

public class AuthMapper {

    public static RegisterMapper register() {
        return new RegisterMapper();
    }

    public static LoginMapper login() {
        return new LoginMapper();
    }

    public static AuthUserMapper auth() {
        return new AuthUserMapper();
    }

}
