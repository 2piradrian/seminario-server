package com.group3.users.presentation.service;

import com.group3.users.domain.dto.profile.request.EditUserProfileReq;
import com.group3.users.domain.dto.profile.response.EditUserProfileRes;

public interface ProfileServiceI {

    EditUserProfileRes create(EditUserProfileReq dto);

}
