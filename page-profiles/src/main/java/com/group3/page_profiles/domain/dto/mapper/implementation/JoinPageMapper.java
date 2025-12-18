package com.group3.page_profiles.domain.dto.mapper.implementation;

import com.group3.page_profiles.domain.dto.request.JoinPageReq;

public class JoinPageMapper {

    public JoinPageReq toRequest(String token, String invitationToken, String pageId){
        return JoinPageReq.create(
            token,
            invitationToken,
            pageId
        );
    }

}
