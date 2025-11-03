package com.group3.users.domain.dto.user.mapper.implementation;

import com.group3.entity.User;
import com.group3.users.domain.dto.user.request.GetUserByIdReq;
import com.group3.users.domain.dto.user.response.GetUserByIdRes;

public class GetByIdMapper {

    public GetUserByIdReq toRequest(String token, String userId) {
        return GetUserByIdReq.create(
            token,
            userId
        );
    }

    public GetUserByIdRes toResponse(User user) {
        return new GetUserByIdRes(
            user.getId(),
            user.getEmail(),
            user.getStatus(),
            user.getRole(),
            user.getProfile()
        );
    }

}
