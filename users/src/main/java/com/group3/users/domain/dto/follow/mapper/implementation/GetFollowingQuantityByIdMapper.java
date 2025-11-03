package com.group3.users.domain.dto.follow.mapper.implementation;


import com.group3.users.domain.dto.follow.request.GetFollowingQuantityByIdReq;
import com.group3.users.domain.dto.follow.response.GetFollowingQuantityByIdRes;

import java.util.Map;

public class GetFollowingQuantityByIdMapper {

    public GetFollowingQuantityByIdReq toRequest(Map<String, Object> payload){
        return GetFollowingQuantityByIdReq.create(
                (String) payload.get("id"),
                (String) payload.get("secret")
        );
    }

    public GetFollowingQuantityByIdRes toResponse(Integer following){
        return new GetFollowingQuantityByIdRes(
                following
        );
    }

}
