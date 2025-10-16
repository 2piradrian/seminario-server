package com.group3.catalog.domain.dto.contentType.mapper;

import com.group3.catalog.domain.dto.contentType.mapper.implementation.GetAllContentTypeMapper;
import com.group3.catalog.domain.dto.contentType.mapper.implementation.GetContentTypeByIdMapper;
import com.group3.catalog.domain.dto.contentType.mapper.implementation.GetContentTypeListByIdMapper;

public class ContentTypeMapper {

    public static GetContentTypeByIdMapper getById() {
        return new GetContentTypeByIdMapper();
    }

    public static GetContentTypeListByIdMapper getListById() {
        return new GetContentTypeListByIdMapper();
    }

    public static GetAllContentTypeMapper getAll() {
        return new GetAllContentTypeMapper();
    }

}
