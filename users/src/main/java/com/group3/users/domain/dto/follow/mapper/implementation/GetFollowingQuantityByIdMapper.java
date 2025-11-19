package com.group3.users.domain.dto.follow.mapper.implementation;


import com.group3.users.domain.dto.follow.request.GetFollowingQuantityByIdReq;
import com.group3.users.domain.dto.follow.response.GetFollowingQuantityByIdRes;

import java.util.Map;

public class GetFollowingQuantityByIdMapper {

    public GetFollowingQuantityByIdReq toRequest(String id, String secret){
        return GetFollowingQuantityByIdReq.create(
                id,
                secret
        );
    }

    public GetFollowingQuantityByIdRes toResponse(Integer following){
        return new GetFollowingQuantityByIdRes(
                following
        );
    }

}
