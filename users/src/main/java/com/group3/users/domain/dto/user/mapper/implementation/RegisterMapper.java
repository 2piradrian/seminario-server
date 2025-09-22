package com.group3.users.domain.dto.user.mapper.implementation;

import com.group3.entity.User;
import com.group3.users.domain.dto.user.request.RegisterUserReq;

import java.util.Map;

public class RegisterMapper {

    public RegisterUserReq toRequest(Map<String, Object> payload) {
        return RegisterUserReq.create(
            (String) payload.get("name"),
            (String) payload.get("surname"),
            (String) payload.get("password"),
            (String) payload.get("email")
        );
    }

}
