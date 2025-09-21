package com.group3.users.domain.dto.user.mapper.implementation;

import com.group3.entity.Token;
import com.group3.users.domain.dto.user.request.LoginUserReq;
import com.group3.users.domain.dto.user.response.LoginUserRes;

import java.util.Map;

public class LoginMapper {

    public LoginUserReq toRequest(Map<String, Object> payload) {
        return LoginUserReq.create(
            (String) payload.get("email"),
            (String) payload.get("password")
        );
    }

    public LoginUserRes toResponse(Token token) {
        return new LoginUserRes(
            token
        );
    }

}
