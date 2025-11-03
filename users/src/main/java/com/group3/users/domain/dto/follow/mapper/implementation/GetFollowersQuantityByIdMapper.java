package com.group3.users.domain.dto.follow.mapper.implementation;


import com.group3.users.domain.dto.follow.request.GetFollowersQuantityByIdReq;
import com.group3.users.domain.dto.follow.response.GetFollowersQuantityByIdRes;

import java.util.Map;

public class GetFollowersQuantityByIdMapper {

    public GetFollowersQuantityByIdReq toRequest(Map<String, Object> payload){
        return GetFollowersQuantityByIdReq.create(
                (String) payload.get("id"),
                (String) payload.get("secret")
        );
    }

    public GetFollowersQuantityByIdRes toResponse(Integer followers){
        return new GetFollowersQuantityByIdRes(
                followers
        );
    }

}
