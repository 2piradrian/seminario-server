package com.group3.profiles.presentation.service;

import com.group3.profiles.domain.dto.profile.request.*;
import com.group3.profiles.domain.dto.profile.response.*;

public interface ProfileServiceI {

    void create(CreateUserProfileReq dto);

    GetUserProfileByIdRes getById(GetUserProfileByIdReq dto);

    GetUserProfilePageByFullnameRes getProfileByFullname(GetUserProfilePageByFullnameReq dto);

    void update(EditUserProfileReq dto);

    void delete(DeleteUserProfileReq dto);

    GetOwnUserProfileRes getOwnProfile (GetOwnUserProfileReq dto);

}
