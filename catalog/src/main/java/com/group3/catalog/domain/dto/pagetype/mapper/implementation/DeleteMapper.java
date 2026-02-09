package com.group3.catalog.domain.dto.pagetype.mapper.implementation;

import com.group3.catalog.domain.dto.pagetype.request.DeletePageTypeReq;

public class DeleteMapper {

    public DeletePageTypeReq toRequest(String token, String id) {
        return DeletePageTypeReq.create(token, id);
    }
}
