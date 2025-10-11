package com.group3.page_profiles.domain.dto.mapper.implementation;

import com.group3.entity.PageProfile;
import com.group3.page_profiles.domain.dto.request.GetPageByUserIdReq;
import com.group3.page_profiles.domain.dto.response.GetPageByUserIdRes;

import java.util.List;

public class GetByUserIdMapper {

    public GetPageByUserIdReq toRequest(String userId) {
        return GetPageByUserIdReq.create(
            userId
        );
    }

    public GetPageByUserIdRes toResponse(List<PageProfile> pages) {
        return new GetPageByUserIdRes(
                pages
        );
    }

}
