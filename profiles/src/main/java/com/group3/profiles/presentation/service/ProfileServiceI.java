package com.group3.profiles.presentation.service;

import com.group3.profiles.domain.dto.profile.request.*;
import com.group3.profiles.domain.dto.profile.response.*;

public interface ProfileServiceI {

    GetUserProfileByIdRes getById(GetUserProfileByIdReq dto);

    EditUserProfileRes update(EditUserProfileReq dto);

    void delete(DeleteUserProfileReq dto);

    GetOwnUserProfileRes getOwnProfile (GetOwnUserProfileReq dto);

}
