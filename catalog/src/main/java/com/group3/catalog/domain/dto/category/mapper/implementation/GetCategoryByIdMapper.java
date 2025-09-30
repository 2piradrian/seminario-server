package com.group3.catalog.domain.dto.category.mapper.implementation;

import com.group3.catalog.domain.dto.category.request.GetCategoryByIdReq;
import com.group3.catalog.domain.dto.category.response.GetCategoryByIdRes;
import com.group3.entity.Category;

public class GetCategoryByIdMapper {

    public GetCategoryByIdReq toRequest(String id){
        return GetCategoryByIdReq.create(
                id
        );
    }

    public GetCategoryByIdRes toResponse(Category category){
        return new GetCategoryByIdRes(
                category
        );
    }
}
