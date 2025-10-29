package com.group3.users.domain.dto.follow.mapper.implementation;


import com.group3.users.domain.dto.follow.request.GetFollowersByIdReq;
import com.group3.users.domain.dto.follow.response.GetFollowersByIdRes;

import java.util.Map;

public class GetFollowersByIdMapper {

    public GetFollowersByIdReq toRequest(Map<String, Object> payload){
        return GetFollowersByIdReq.create(
                (String) payload.get("id"),
                (String) payload.get("secret")
        );
    }

    public GetFollowersByIdRes toResponse(Integer followers){
        return new GetFollowersByIdRes(
                followers
        );
    }

}
