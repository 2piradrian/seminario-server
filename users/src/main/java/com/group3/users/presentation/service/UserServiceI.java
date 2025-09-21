package com.group3.users.presentation.service;

import com.group3.users.domain.dto.user.request.*;
import com.group3.users.domain.dto.user.response.AuthUserRes;
import com.group3.users.domain.dto.user.response.GetUserByIdRes;
import com.group3.users.domain.dto.user.response.LoginUserRes;
import com.group3.users.domain.dto.user.response.RegisterUserRes;

public interface UserServiceI {

    GetUserByIdRes getById(GetUserByIdReq dto);

    RegisterUserRes register(RegisterUserReq dto);

    LoginUserRes login(LoginUserReq dto);

    AuthUserRes auth(AuthUserReq dto);

    void delete(DeleteUserReq dto);

}
