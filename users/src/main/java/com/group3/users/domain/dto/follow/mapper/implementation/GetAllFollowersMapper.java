package com.group3.users.domain.dto.follow.mapper.implementation;

import com.group3.entity.Follow;
import com.group3.users.domain.dto.follow.request.GetAllFollowersReq;
import com.group3.users.domain.dto.follow.response.GetAllFollowersRes;

import java.util.List;
import java.util.Map;

public class GetAllFollowersMapper {

    public GetAllFollowersReq toRequest(Map<String, Object> payload) {
        return GetAllFollowersReq.create(
                (String) payload.get("id"),
                (String) payload.get("secret")
        );
    }

    public GetAllFollowersRes toResponse(List<Follow> followers) {
        return new GetAllFollowersRes(followers);
    }

}
