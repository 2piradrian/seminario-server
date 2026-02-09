package com.group3.catalog.domain.dto.category.mapper.implementation;

import com.group3.catalog.domain.dto.category.request.CreateCategoryReq;
import com.group3.catalog.domain.dto.category.response.CreateCategoryRes;
import com.group3.entity.Category;

import java.util.Map;

public class CreateMapper {

    public CreateCategoryReq toRequest(String token, Map<String, Object> payload) {
        return CreateCategoryReq.create(
                token,
                (String) payload.get("name")
        );
    }

    public CreateCategoryRes toResponse(Category category) {
        return new CreateCategoryRes(category);
    }
}
