package com.group3.users.domain.dto.auth.mapper.implementation;

import com.group3.users.domain.dto.auth.request.GrantRoleUserReq;

import java.util.Map;

public class GrantUserMapper {

    public GrantRoleUserReq toRequest(String token, Map<String, Object> payload) {
        return GrantRoleUserReq.create(
            token,
            (String) payload.get("email"),
            (String) payload.get("roleId")
        );
    }

}
