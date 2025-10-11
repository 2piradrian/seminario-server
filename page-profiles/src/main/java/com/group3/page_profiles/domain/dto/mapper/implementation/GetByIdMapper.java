package com.group3.page_profiles.domain.dto.mapper.implementation;

import com.group3.entity.PageProfile;
import com.group3.entity.PageProfile;
import com.group3.page_profiles.domain.dto.request.GetPageByIdReq;
import com.group3.page_profiles.domain.dto.response.GetPageByIdRes;

public class GetByIdMapper {

    public GetPageByIdReq toRequest(String pageId) {
        return GetPageByIdReq.create(
            pageId
        );
    }

    public GetPageByIdRes toResponse(PageProfile page) {
        return new GetPageByIdRes(
            page.getId(),
            page.getName(),
            page.getPortraitImage(),
            page.getProfileImage(),
            page.getShortDescription(),
            page.getLongDescription(),
            page.getOwner(),
            page.getMembers(),
            page.getPageType()
        );
    }
    
}
