package com.group3.pages.domain.dto.mapper.implementation;

import com.group3.entity.Page;
import com.group3.pages.domain.dto.request.GetPageByIdReq;
import com.group3.pages.domain.dto.response.GetPageByIdRes;

public class GetByIdMapper {

    public GetPageByIdReq toRequest(String pageId) {
        return GetPageByIdReq.create(
            pageId
        );
    }

    public GetPageByIdRes toResponse(Page page) {
        return new GetPageByIdRes(
            page.getId(),
            page.getName(),
            page.getImageId(),
            page.getOwnerId(),
            page.getMembers()
        );
    }
    
}
