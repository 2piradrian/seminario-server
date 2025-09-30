package com.group3.pages.domain.dto.mapper.implementation;

import com.group3.entity.Page;
import com.group3.pages.domain.dto.request.GetPageByUserIdReq;
import com.group3.pages.domain.dto.response.GetPageByUserIdRes;

import java.util.List;

public class GetByUserIdMapper {

    public GetPageByUserIdReq toRequest(String userId) {
        return GetPageByUserIdReq.create(
            userId
        );
    }

    public GetPageByUserIdRes toResponse(List<Page> pages) {
        return new GetPageByUserIdRes(
            pages
        );
    }

}
