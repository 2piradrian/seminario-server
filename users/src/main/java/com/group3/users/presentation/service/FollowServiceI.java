package com.group3.users.presentation.service;

import com.group3.users.domain.dto.follow.request.*;
import com.group3.users.domain.dto.follow.response.*;

public interface FollowServiceI {

    void toggleFollow(ToggleFollowReq dto);

    GetFollowerPageRes getFollowers(GetFollowerPageReq dto);

    GetFollowingPageRes getFollowing(GetFollowingPageReq dto);

    GetFollowersQuantityByIdRes getFollowersQuantityById(GetFollowersQuantityByIdReq dto);

    GetFollowingQuantityByIdRes getFollowingQuantityById(GetFollowingQuantityByIdReq dto);

    GetAllFollowersRes getAllFollowers(GetAllFollowersReq dto);

    GetAllFollowingRes getAllFollowing(GetAllFollowingReq dto);

}
