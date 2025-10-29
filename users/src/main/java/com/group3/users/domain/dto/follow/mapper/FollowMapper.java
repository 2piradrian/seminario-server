package com.group3.users.domain.dto.follow.mapper;


import com.group3.users.domain.dto.follow.mapper.implementation.*;

public class FollowMapper {

    public static GetFollowerPageMapper getFollowerPage() {
        return new GetFollowerPageMapper();
    }

    public static GetFollowersQuantityByIdMapper getFollowersQuantityById(){
        return new GetFollowersQuantityByIdMapper();
    }

    public static GetFollowingPageMapper getFollowingPage() {
        return new GetFollowingPageMapper();
    }

    public static GetFollowingQuantityByIdMapper getFollowingQuantityById() {
        return new GetFollowingQuantityByIdMapper();
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
