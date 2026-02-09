package com.group3.users.domain.dto.banneduser.mapper.implementation;

import com.group3.users.domain.dto.banneduser.request.BanUserReq;

public class BanUserMapper {

    public BanUserReq toRequest(String token, String userId, String reason) {
        return BanUserReq.create(token, userId, reason);
    }

}