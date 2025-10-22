package com.group3.catalog.domain.dto.contentType.mapper.implementation;

import com.group3.catalog.domain.dto.contentType.request.GetContentTypeByIdReq;
import com.group3.catalog.domain.dto.contentType.response.GetContentTypeByIdRes;
import com.group3.entity.ContentType;

public class GetContentTypeByIdMapper {

    public GetContentTypeByIdReq toRequest(String id){
        return GetContentTypeByIdReq.create(
            id
        );
    }

    public GetContentTypeByIdRes toResponse(ContentType contentType){
        return new GetContentTypeByIdRes(
            contentType
        );
    }

}
