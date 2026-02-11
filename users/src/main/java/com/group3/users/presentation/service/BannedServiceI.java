package com.group3.users.presentation.service;

import com.group3.users.domain.dto.banneduser.request.BanUserReq;
import com.group3.users.domain.dto.banneduser.request.GetAllBannedUserPageReq;
import com.group3.users.domain.dto.banneduser.response.GetAllBannedUserPageRes;

public interface BannedServiceI {
    void banUser(BanUserReq banUserReq) throws Exception;
    GetAllBannedUserPageRes getAllBannedUsers(GetAllBannedUserPageReq getAllBannedUserPageReq);
}
