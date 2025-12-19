package com.group3.page_profiles.domain.dto.mapper.implementation;

import com.group3.page_profiles.domain.dto.request.LeavePageReq;

public class LeavePageMapper {

    public LeavePageReq toRequest(String token, String pageId){
        return LeavePageReq.create(
            token,
            pageId
        );
    }

}
