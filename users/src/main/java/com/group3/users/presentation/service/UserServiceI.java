package com.group3.users.presentation.service;

import com.group3.users.domain.dto.user.request.*;
import com.group3.users.domain.dto.user.response.*;

public interface UserServiceI {

    GetUserByIdRes getById(GetUserByIdReq dto);

    RegisterUserRes register(RegisterUserReq dto);

    LoginUserRes login(LoginUserReq dto);

    EditUserRes update(EditUserReq dto);

    void delete(DeleteUserReq dto);

}
