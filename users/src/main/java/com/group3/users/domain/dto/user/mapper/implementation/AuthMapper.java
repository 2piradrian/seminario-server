package com.group3.users.domain.dto.user.mapper.implementation;

import com.group3.entity.User;
import com.group3.users.domain.dto.user.request.AuthUserReq;
import com.group3.users.domain.dto.user.response.AuthUserRes;

public class AuthMapper {

    public AuthUserReq toRequest(String token){
        return AuthUserReq.create(
            token
        );
    }

    public AuthUserRes toResponse(User user){
        return new AuthUserRes(
            user.getId(),
            user.getEmail(),
            user.getRoles()
        );
    }

}
