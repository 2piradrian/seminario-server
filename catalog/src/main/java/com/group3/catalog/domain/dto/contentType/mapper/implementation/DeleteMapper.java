package com.group3.catalog.domain.dto.contentType.mapper.implementation;

import com.group3.catalog.domain.dto.contentType.request.DeleteContentTypeReq;

public class DeleteMapper {

    public DeleteContentTypeReq toRequest(String token, String id) {
        return DeleteContentTypeReq.create(token, id);
    }
}
