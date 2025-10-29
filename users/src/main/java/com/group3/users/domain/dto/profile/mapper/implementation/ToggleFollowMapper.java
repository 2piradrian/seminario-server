package com.group3.users.domain.dto.profile.mapper.implementation;


import com.group3.users.domain.dto.profile.request.ToggleFollowReq;

import java.util.Map;

public class ToggleFollowMapper {

    public ToggleFollowReq toRequest(String token, Map<String, Object> payload){
        return ToggleFollowReq.create(
            token,
            (String) payload.get("id")
        );
    }

}
