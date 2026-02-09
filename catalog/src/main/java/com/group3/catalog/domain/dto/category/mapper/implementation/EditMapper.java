package com.group3.catalog.domain.dto.category.mapper.implementation;

import com.group3.catalog.domain.dto.category.request.EditCategoryReq;
import com.group3.catalog.domain.dto.category.response.EditCategoryRes;
import com.group3.entity.Category;

import java.util.Map;

public class EditMapper {

    public EditCategoryReq toRequest(String token, String id, Map<String, Object> payload) {
        return EditCategoryReq.create(
                token,
                id,
                (String) payload.get("name")
        );
    }

    public EditCategoryRes toResponse(Category category) {
        return new EditCategoryRes(category);
    }
}
