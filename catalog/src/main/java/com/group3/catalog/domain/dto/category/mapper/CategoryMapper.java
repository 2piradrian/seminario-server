package com.group3.catalog.domain.dto.category.mapper;

import com.group3.catalog.domain.dto.category.mapper.implementation.GetAllCategoryMapper;
import com.group3.catalog.domain.dto.category.mapper.implementation.GetCategoryByIdMapper;
import com.group3.catalog.domain.dto.category.mapper.implementation.GetCategoryListByIdMapper;

public class CategoryMapper {

    public static GetCategoryByIdMapper getById(){ return new GetCategoryByIdMapper(); }

    public static GetCategoryListByIdMapper getListById() { return new GetCategoryListByIdMapper(); }

    public static GetAllCategoryMapper getAll() { return new GetAllCategoryMapper(); }
}
