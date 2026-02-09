package com.group3.users.domain.dto.banneduser.mapper;

import com.group3.users.domain.dto.banneduser.mapper.implementation.BanUserMapper;
import com.group3.users.domain.dto.banneduser.mapper.implementation.GetAllBannedUserPageMapper;

public class BannedUserMapper {

    public static BanUserMapper banUser() {
        return new BanUserMapper();
    }

    public static GetAllBannedUserPageMapper getAllBannedUserPage() {
        return new GetAllBannedUserPageMapper();
    }

}