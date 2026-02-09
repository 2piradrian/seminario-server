package com.group3.users.domain.dto.banneduser.mapper.implementation;

import com.group3.entity.BannedUser;
import com.group3.entity.PageContent;
import com.group3.users.domain.dto.banneduser.request.GetAllBannedUserPageReq;
import com.group3.users.domain.dto.banneduser.response.GetAllBannedUserPageRes;

public class GetAllBannedUserPageMapper {

    public GetAllBannedUserPageReq toRequest(String token, Integer page, Integer size) {
        return GetAllBannedUserPageReq.create(
                token,
                page,
                size
        );
    }

    public GetAllBannedUserPageRes toResponse(PageContent<BannedUser> pageContent) {
        return new GetAllBannedUserPageRes(
                pageContent.getContent(),
                pageContent.getNextPage()
        );
    }
}
