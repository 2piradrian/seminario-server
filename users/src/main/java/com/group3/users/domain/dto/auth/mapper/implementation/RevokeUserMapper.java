package com.group3.users.domain.dto.auth.mapper.implementation;

import com.group3.users.domain.dto.auth.request.RevokeRoleUserReq;

import java.util.Map;

public class RevokeUserMapper {

    public RevokeRoleUserReq toRequest(String token, Map<String, Object> payload) {
        return RevokeRoleUserReq.create(
            token,
            (String) payload.get("email")
        );
    }

}
