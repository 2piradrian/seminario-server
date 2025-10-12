package com.group3.user_profiles.presentation.service;

import com.group3.user_profiles.domain.dto.profile.request.*;
import com.group3.user_profiles.domain.dto.profile.response.*;

public interface UserProfileServiceI {

    GetFollowerPageRes getFollowers(GetFollowerPageReq dto);

    GetFollowingPageRes getFollowing(GetFollowingPageReq dto);

    void create(CreateUserProfileReq dto);

    GetUserProfileByIdRes getById(GetUserProfileByIdReq dto);

    void update(EditUserProfileReq dto);

    void delete(DeleteUserProfileReq dto);

    GetOwnUserProfileRes getOwnProfile (GetOwnUserProfileReq dto);

}
