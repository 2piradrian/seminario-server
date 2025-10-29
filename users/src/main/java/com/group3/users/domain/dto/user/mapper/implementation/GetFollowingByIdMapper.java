package com.group3.users.domain.dto.user.mapper.implementation;


import com.group3.users.domain.dto.profile.request.GetFollowersByIdReq;
import com.group3.users.domain.dto.profile.response.GetFollowersByIdRes;
import com.group3.users.domain.dto.user.request.GetFollowingByIdReq;
import com.group3.users.domain.dto.user.response.GetFollowingByIdRes;

import java.util.Map;

public class GetFollowingByIdMapper {

    public GetFollowingByIdReq toRequest(Map<String, Object> payload){
        return GetFollowingByIdReq.create(
                (String) payload.get("id"),
                (String) payload.get("secret")
        );
    }

    public GetFollowingByIdRes toResponse(Integer followers){
        return new GetFollowingByIdRes(
                followers
        );
    }

}
