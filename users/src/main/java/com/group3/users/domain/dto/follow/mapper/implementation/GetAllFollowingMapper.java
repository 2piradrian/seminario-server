package com.group3.users.domain.dto.follow.mapper.implementation;

import com.group3.entity.Follow;
import com.group3.users.domain.dto.follow.request.GetAllFollowingReq;
import com.group3.users.domain.dto.follow.response.GetAllFollowingRes;

import java.util.List;
import java.util.Map;

public class GetAllFollowingMapper {

    public GetAllFollowingReq toRequest(String id, String secret) {
        return GetAllFollowingReq.create(
                id,
                secret
        );
    }

    public GetAllFollowingRes toResponse(List<Follow> following) {
        return new GetAllFollowingRes(following);
    }

}
