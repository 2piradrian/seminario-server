package com.group3.users.domain.dto.profile.mapper.implementation;


import com.group3.users.domain.dto.profile.request.DeleteUserProfileReq;

public class DeleteMapper {

    public DeleteUserProfileReq toRequest(String token) {
        return DeleteUserProfileReq.create(
            token
        );
    }

}
