package com.group3.catalog.domain.dto.contentType.mapper;

import com.group3.catalog.domain.dto.contentType.mapper.implementation.CreateMapper;
import com.group3.catalog.domain.dto.contentType.mapper.implementation.DeleteMapper;
import com.group3.catalog.domain.dto.contentType.mapper.implementation.EditMapper;
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

    public static CreateMapper create() {
        return new CreateMapper();
    }

    public static EditMapper edit() {
        return new EditMapper();
    }

    public static DeleteMapper delete() {
        return new DeleteMapper();
    }

}
