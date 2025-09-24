package com.group3.catalog.domain.dto.style.mapper.implementation;

import com.group3.catalog.domain.dto.style.request.GetStyleByIdReq;
import com.group3.catalog.domain.dto.style.response.GetStyleByIdRes;
import com.group3.entity.Style;

public class GetStyleByIdMapper {

    public GetStyleByIdReq toRequest(String id){
        return GetStyleByIdReq.create(
            id
        );
    }

    public GetStyleByIdRes toResponse(Style style){
        return new GetStyleByIdRes(
            style
        );
    }

}
