package com.group3.users.domain.dto.user.mapper.implementation;

import com.group3.entity.User;
import com.group3.users.domain.dto.user.request.GetUserMutualsFollowersReq;
import com.group3.users.domain.dto.user.response.GetUserMutualsFollowersRes;

import java.util.List;

public class GetMutualsFollowersMapper {

    public GetUserMutualsFollowersReq toRequest(String token, String userId) {
        return GetUserMutualsFollowersReq.create(
            token,
            userId
        );
    }

    public GetUserMutualsFollowersRes toResponse(List<User> mutualsFollowers) {
        return new GetUserMutualsFollowersRes(mutualsFollowers);
    }

}
