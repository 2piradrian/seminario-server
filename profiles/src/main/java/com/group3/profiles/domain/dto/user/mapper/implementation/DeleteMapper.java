package com.group3.profiles.domain.dto.user.mapper.implementation;

import com.group3.profiles.domain.dto.user.request.DeleteUserReq;

public class DeleteMapper {

    public DeleteUserReq toRequest(String token) {
        return DeleteUserReq.create(
            token
        );
    }

}
