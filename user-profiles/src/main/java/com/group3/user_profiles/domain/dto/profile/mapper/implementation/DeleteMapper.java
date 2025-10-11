package com.group3.user_profiles.domain.dto.profile.mapper.implementation;

import com.group3.user_profiles.domain.dto.profile.request.DeleteUserProfileReq;

public class DeleteMapper {

    public DeleteUserProfileReq toRequest(String token) {
        return DeleteUserProfileReq.create(
            token
        );
    }

}
