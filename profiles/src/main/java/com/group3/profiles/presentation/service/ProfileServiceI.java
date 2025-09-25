package com.group3.profiles.presentation.service;

import com.group3.profiles.domain.dto.user.request.*;
import com.group3.profiles.domain.dto.user.response.*;

public interface ProfileServiceI {

    GetUserByIdRes getById(GetUserByIdReq dto);

    EditUserRes update(EditUserReq dto);

    void delete(DeleteUserReq dto);

    GetOwnProfileRes getOwnProfile (GetOwnProfileReq dto);

}
