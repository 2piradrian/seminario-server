package com.group3.users.domain.dto.auth.mapper.implementation;

import com.group3.users.domain.dto.auth.request.LoginUserReq;
import com.group3.users.domain.dto.auth.response.LoginUserRes;
import com.group3.entity.Token;

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
