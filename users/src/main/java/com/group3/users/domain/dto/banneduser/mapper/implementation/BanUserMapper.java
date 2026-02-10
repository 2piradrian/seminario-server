package com.group3.users.domain.dto.banneduser.mapper.implementation;

import com.group3.users.domain.dto.banneduser.request.BanUserReq;

import java.util.Map;

public class BanUserMapper {

    public BanUserReq toRequest(String token, Map<String, Object> payload) {
        return BanUserReq.create(
                token,
                (String) payload.get("userId"),
                (String) payload.get("reasonId")
                );
    }

}