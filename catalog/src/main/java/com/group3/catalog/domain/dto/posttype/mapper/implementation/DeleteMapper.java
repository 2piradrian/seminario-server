package com.group3.catalog.domain.dto.posttype.mapper.implementation;

import com.group3.catalog.domain.dto.posttype.request.DeletePostTypeReq;

public class DeleteMapper {

    public DeletePostTypeReq toRequest(String token, String id) {
        return DeletePostTypeReq.create(token, id);
    }
}
