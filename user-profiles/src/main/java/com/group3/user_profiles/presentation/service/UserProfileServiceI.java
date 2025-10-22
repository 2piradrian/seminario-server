package com.group3.user_profiles.presentation.service;

import com.group3.user_profiles.domain.dto.profile.request.*;
import com.group3.user_profiles.domain.dto.profile.response.*;

public interface UserProfileServiceI {

    void create(CreateUserProfileReq dto);

    GetUserProfileByIdRes getById(GetUserProfileByIdReq dto);

    GetUserProfileWithFollowingByIdRes getById(GetUserProfileWithFollowingByIdReq dto);

    GetUserProfilePageFilteredRes getProfileFiltered(GetUserProfilePageFilteredReq dto);

    GetOwnUserProfileRes getOwnProfile (GetOwnUserProfileReq dto);

    GetFollowerPageRes getFollowers(GetFollowerPageReq dto);

    GetFollowingPageRes getFollowing(GetFollowingPageReq dto);

    GetFollowersByIdRes getFollowersById(GetFollowersByIdReq dto);

    void toggleFollow(ToggleFollowReq dto);

    void active(ActiveUserProfileReq dto);

    void update(EditUserProfileReq dto);

    void delete(DeleteUserProfileReq dto);


}
