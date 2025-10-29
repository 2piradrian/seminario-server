package com.group3.users.domain.dto.profile.mapper;


import com.group3.users.domain.dto.profile.mapper.implementation.*;
import com.group3.users.domain.dto.user.mapper.implementation.GetPageFilteredMapper;

public class UserProfileMapper {

    public static GetFollowerPageMapper getFollowerPage() {
        return new GetFollowerPageMapper();
    }

    public static GetFollowingPageMapper getFollowingPage() {
        return new GetFollowingPageMapper();
    }

    public static ToggleFollowMapper toggleFollow() {
        return new ToggleFollowMapper();
    }

    public static GetFollowersByIdMapper getFollowersById() {
        return new GetFollowersByIdMapper();
    }

    public static GetByIdMapper getById() {
        return new GetByIdMapper();
    }

    public static GetByIdWithFollowingMapper getByIdWithFollowing() {
        return new GetByIdWithFollowingMapper();
    }

    public static CreateMapper create(){
        return new CreateMapper();
    }

    public static DeleteMapper delete() {
        return new DeleteMapper();
    }

    public static EditMapper update() {
        return new EditMapper();
    }

    public static ActiveMapper active() {
        return new ActiveMapper();
    }

}
