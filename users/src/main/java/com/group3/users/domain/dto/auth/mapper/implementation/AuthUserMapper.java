package com.group3.users.domain.dto.auth.mapper.implementation;

import com.group3.users.domain.dto.auth.request.AuthUserReq;
import com.group3.users.domain.dto.auth.response.AuthUserRes;
import com.group3.entity.User;

public class AuthUserMapper {

    public AuthUserReq toRequest(String token){
        return AuthUserReq.create(
            token
        );
    }

    public AuthUserRes toResponse(User user){
        return new AuthUserRes(
            user.getId(),
            user.getEmail(),
            user.getStatus(),
            user.getRole()
        );
    }

}
