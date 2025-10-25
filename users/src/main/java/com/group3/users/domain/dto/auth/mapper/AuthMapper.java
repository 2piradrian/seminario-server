package com.group3.users.domain.dto.auth.mapper;

import com.group3.users.domain.dto.auth.mapper.implementation.*;

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

    public static VerifyEmailMapper verifyEmail() {
        return new VerifyEmailMapper();
    }

    public static ResendEmailMapper resendEmail() {
        return new ResendEmailMapper();
    }

    public static GrantUserMapper grantRole() {
        return new GrantUserMapper();
    }

    public static RevokeUserMapper revokeRole() {
        return new RevokeUserMapper();
    }
}
