package com.group3.catalog.domain.dto.style.mapper.implementation;

import com.group3.catalog.domain.dto.style.request.DeleteStyleReq;

public class DeleteMapper {

    public DeleteStyleReq toRequest(String token, String id) {
        return DeleteStyleReq.create(token, id);
    }
}
