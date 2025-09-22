package com.group3.users.presentation.service;

import com.group3.users.domain.dto.user.request.AuthUserReq;
import com.group3.users.domain.dto.user.request.LoginUserReq;
import com.group3.users.domain.dto.user.request.RegisterUserReq;
import com.group3.users.domain.dto.user.response.AuthUserRes;
import com.group3.users.domain.dto.user.response.LoginUserRes;

public interface AuthServiceI {

    AuthUserRes auth(AuthUserReq dto);

    void register(RegisterUserReq dto);

    LoginUserRes login(LoginUserReq dto);

}
