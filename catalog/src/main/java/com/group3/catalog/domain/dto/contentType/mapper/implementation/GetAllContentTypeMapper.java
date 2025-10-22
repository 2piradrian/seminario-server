package com.group3.catalog.domain.dto.contentType.mapper.implementation;

import com.group3.catalog.domain.dto.contentType.response.GetAllContentTypeRes;
import com.group3.entity.ContentType;

import java.util.List;

public class GetAllContentTypeMapper {

    public GetAllContentTypeRes toResponse(List<ContentType> contentTypes){
        return new GetAllContentTypeRes(
            contentTypes
        );
    }

}
