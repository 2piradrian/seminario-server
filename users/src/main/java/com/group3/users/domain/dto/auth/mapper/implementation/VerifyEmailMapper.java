package com.group3.users.domain.dto.auth.mapper.implementation;

import com.group3.users.domain.dto.auth.request.VerifyEmailReq;

public class VerifyEmailMapper {

    public VerifyEmailReq toRequest(String token){
        return VerifyEmailReq.create(
            token
        );
    }

}
