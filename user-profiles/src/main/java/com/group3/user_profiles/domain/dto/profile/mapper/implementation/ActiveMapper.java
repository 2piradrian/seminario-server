package com.group3.user_profiles.domain.dto.profile.mapper.implementation;

import com.group3.user_profiles.domain.dto.profile.request.ActiveUserProfileReq;

import java.util.Map;

public class ActiveMapper {

    public ActiveUserProfileReq toRequest(Map<String, Object> payload){
        return ActiveUserProfileReq.create(
            (String) payload.get("userId"),
            (String) payload.get("secret")
        );
    }

}
