package com.group3.users.domain.dto.follow.mapper;


import com.group3.users.domain.dto.follow.mapper.implementation.*;

public class FollowMapper {

    public static GetFollowerPageMapper getFollowerPage() {
        return new GetFollowerPageMapper();
    }

    public static GetFollowersByIdMapper getFollowersById(){
        return new GetFollowersByIdMapper();
    }

    public static GetFollowingPageMapper getFollowingPage() {
        return new GetFollowingPageMapper();
    }

    public static GetFollowingByIdMapper getFollowingById() {
        return new GetFollowingByIdMapper();
    }

    public static ToggleFollowMapper toggleFollow() {
        return new ToggleFollowMapper();
    }

    public static GetAllFollowersMapper getAllFollowers() {
        return new GetAllFollowersMapper();
    }

    public static GetAllFollowingMapper getAllFollowing() {
        return new GetAllFollowingMapper();
    }

}
