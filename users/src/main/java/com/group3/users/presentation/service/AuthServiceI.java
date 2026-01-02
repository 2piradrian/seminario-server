package com.group3.users.presentation.service;

import com.group3.entity.User;
import com.group3.users.domain.dto.auth.request.*;
import com.group3.users.domain.dto.auth.response.AuthUserRes;
import com.group3.users.domain.dto.auth.response.LoginUserRes;

public interface AuthServiceI {

    // Exposed Auth
    AuthUserRes auth(AuthUserReq dto);

    // Local Auth
    User auth(String reqToken);

    void register(RegisterUserReq dto);

    LoginUserRes login(LoginUserReq dto);

    String verifyEmail(VerifyEmailReq dto);

    void resendVerifyEmail(ResendEmailReq dto);

    void grantRole(GrantRoleUserReq dto);

    void revokeRole(RevokeRoleUserReq dto);

    void recoverPassword(RecoverPasswordReq dto);

    void changePassword(ChangePasswordReq dto);

}
