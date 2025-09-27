package com.group3.users.domain.dto.auth.mapper.implementation;

import com.group3.users.domain.dto.auth.request.ResendEmailReq;

import java.util.Map;

public class ResendEmailMapper {

    public ResendEmailReq toRequest(Map<String, Object> payload){
        return ResendEmailReq.create(
            (String) payload.get("email")
        );
    }

}
