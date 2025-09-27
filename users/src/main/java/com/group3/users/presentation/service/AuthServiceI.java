package com.group3.users.presentation.service;

import com.group3.users.domain.dto.auth.request.*;
import com.group3.users.domain.dto.auth.response.AuthUserRes;
import com.group3.users.domain.dto.auth.response.LoginUserRes;

public interface AuthServiceI {

    AuthUserRes auth(AuthUserReq dto);

    void register(RegisterUserReq dto);

    LoginUserRes login(LoginUserReq dto);

    void verifyEmail(VerifyEmailReq dto);

    void resendVerifyEmail(ResendEmailReq dto);

}
