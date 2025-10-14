package com.group3.user_profiles.domain.dto.profile.mapper.implementation;

import com.group3.user_profiles.domain.dto.profile.request.GetFollowersByIdReq;
import com.group3.user_profiles.domain.dto.profile.response.GetFollowersByIdRes;

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
