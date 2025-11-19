package com.group3.catalog.domain.dto.posttype.mapper;

import com.group3.catalog.domain.dto.posttype.mapper.implementation.GetAllPostTypeMapper;
import com.group3.catalog.domain.dto.posttype.mapper.implementation.GetPostTypeByIdMapper;
import com.group3.catalog.domain.dto.posttype.mapper.implementation.GetPostTypeListByIdMapper;

public class PostTypeMapper {

    public static GetPostTypeByIdMapper getById(){ return new GetPostTypeByIdMapper(); }

    public static GetPostTypeListByIdMapper getListById() { return new GetPostTypeListByIdMapper(); }

    public static GetAllPostTypeMapper getAll() { return new GetAllPostTypeMapper(); }
}
