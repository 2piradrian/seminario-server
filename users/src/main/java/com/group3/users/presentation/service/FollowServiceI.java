package com.group3.users.presentation.service;

import com.group3.users.domain.dto.follow.request.*;
import com.group3.users.domain.dto.follow.response.*;

public interface FollowServiceI {

    void toggleFollow(ToggleFollowReq dto);

    GetFollowerPageRes getFollowers(GetFollowerPageReq dto);

    GetFollowingPageRes getFollowing(GetFollowingPageReq dto);

    GetFollowersByIdRes getFollowersById(GetFollowersByIdReq dto);

    GetFollowingByIdRes getFollowingById(GetFollowingByIdReq dto);

    GetAllFollowersRes getAllFollowers(GetAllFollowersReq dto);

    GetAllFollowingRes getAllFollowing(GetAllFollowingReq dto);

}
