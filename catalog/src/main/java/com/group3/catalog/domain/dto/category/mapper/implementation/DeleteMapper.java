package com.group3.catalog.domain.dto.category.mapper.implementation;

import com.group3.catalog.domain.dto.category.request.DeleteCategoryReq;

public class DeleteMapper {

    public DeleteCategoryReq toRequest(String token, String id) {
        return DeleteCategoryReq.create(token, id);
    }
}
