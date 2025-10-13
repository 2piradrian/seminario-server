package com.group3.user_profiles.domain.dto.profile.mapper.implementation;

import com.group3.user_profiles.domain.dto.profile.request.ActiveUserProfileReq;

public class ActiveMapper {

    public ActiveUserProfileReq toRequest(String userId){
        return ActiveUserProfileReq.create(userId);
    }

}
