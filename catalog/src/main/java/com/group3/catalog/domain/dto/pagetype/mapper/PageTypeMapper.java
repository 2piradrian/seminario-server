package com.group3.catalog.domain.dto.pagetype.mapper;

import com.group3.catalog.domain.dto.pagetype.mapper.implementation.GetAllPageTypeMapper;
import com.group3.catalog.domain.dto.pagetype.mapper.implementation.GetPageTypeByIdMapper;
import com.group3.catalog.domain.dto.pagetype.mapper.implementation.GetPageTypeListByIdMapper;

public class PageTypeMapper {

    public static GetPageTypeByIdMapper getById(){ return new GetPageTypeByIdMapper(); }

    public static GetPageTypeListByIdMapper getListById() { return new GetPageTypeListByIdMapper(); }

    public static GetAllPageTypeMapper getAll() { return new GetAllPageTypeMapper(); }
}
