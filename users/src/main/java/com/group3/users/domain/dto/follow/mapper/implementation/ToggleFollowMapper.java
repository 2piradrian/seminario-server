package com.group3.users.domain.dto.follow.mapper.implementation;

import com.group3.users.domain.dto.follow.request.ToggleFollowReq;

import java.util.Map;

public class ToggleFollowMapper {

    public ToggleFollowReq toRequest(String token, Map<String, Object> payload){
        return ToggleFollowReq.create(
            token,
            (String) payload.get("id")
        );
    }

}
