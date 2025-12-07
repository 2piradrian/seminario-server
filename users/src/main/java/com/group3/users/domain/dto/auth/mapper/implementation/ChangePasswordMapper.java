package com.group3.users.domain.dto.auth.mapper.implementation;

import com.group3.users.domain.dto.auth.request.ChangePasswordReq;

import java.util.Map;

public class ChangePasswordMapper {

    public ChangePasswordReq toRequest(String token, Map<String, Object> payload){
        return ChangePasswordReq.create(
            token,
            (String) payload.get("password")
        );
    }

}
