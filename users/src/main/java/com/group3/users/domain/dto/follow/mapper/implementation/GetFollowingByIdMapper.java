package com.group3.users.domain.dto.follow.mapper.implementation;


import com.group3.users.domain.dto.follow.request.GetFollowingByIdReq;
import com.group3.users.domain.dto.follow.response.GetFollowingByIdRes;

import java.util.Map;

public class GetFollowingByIdMapper {

    public GetFollowingByIdReq toRequest(Map<String, Object> payload){
        return GetFollowingByIdReq.create(
                (String) payload.get("id"),
                (String) payload.get("secret")
        );
    }

    public GetFollowingByIdRes toResponse(Integer following){
        return new GetFollowingByIdRes(
                following
        );
    }

}
