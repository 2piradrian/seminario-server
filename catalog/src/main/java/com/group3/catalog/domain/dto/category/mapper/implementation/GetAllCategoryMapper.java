package com.group3.catalog.domain.dto.category.mapper.implementation;

import com.group3.catalog.domain.dto.category.response.GetAllCategoryRes;
import com.group3.entity.Category;

import java.util.List;

public class GetAllCategoryMapper {

    public GetAllCategoryRes toResponse(List<Category> category){
        return new GetAllCategoryRes(
                category
        );
    }
}
