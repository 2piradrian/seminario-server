package com.group3.catalog.domain.dto.pagetype.mapper.implementation;

import com.group3.catalog.domain.dto.pagetype.request.GetPageTypeByIdReq;
import com.group3.catalog.domain.dto.pagetype.response.GetPageTypeByIdRes;
import com.group3.entity.PageType;

public class GetPageTypeByIdMapper {

    public GetPageTypeByIdReq toRequest(String id){
        return GetPageTypeByIdReq.create(
                id
        );
    }

    public GetPageTypeByIdRes toResponse(PageType pageType){
        return new GetPageTypeByIdRes(
                pageType
        );
    }
}
