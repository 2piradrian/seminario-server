package com.group3.catalog.domain.dto.posttype.mapper;

import com.group3.catalog.domain.dto.posttype.mapper.implementation.*;

public class PostTypeMapper {

    public static GetPostTypeByIdMapper getById(){ return new GetPostTypeByIdMapper(); }

    public static GetPostTypeListByIdMapper getListById() { return new GetPostTypeListByIdMapper(); }

    public static GetAllPostTypeMapper getAll() { return new GetAllPostTypeMapper(); }

    public static CreateMapper create() { return new CreateMapper(); }

    public static EditMapper edit() { return new EditMapper(); }

    public static DeleteMapper delete() { return new DeleteMapper(); }
}
