package com.group3.users.domain.dto.auth.mapper.implementation;

import com.group3.users.domain.dto.auth.request.RecoverPasswordReq;

import java.util.Map;

public class RecoverMapper {

    public RecoverPasswordReq toRequest(Map<String, Object> payload){
        return RecoverPasswordReq.create(
            (String) payload.get("email")
        );
    }

}
