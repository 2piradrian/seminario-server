package com.group3.catalog.domain.dto.contentType.mapper.implementation;

import com.group3.catalog.domain.dto.contentType.request.CreateContentTypeReq;
import com.group3.catalog.domain.dto.contentType.response.CreateContentTypeRes;
import com.group3.entity.ContentType;

import java.util.Map;

public class CreateMapper {

    public CreateContentTypeReq toRequest(String token, Map<String, Object> payload) {
        return CreateContentTypeReq.create(
                token,
                (String) payload.get("name")
        );
    }

    public CreateContentTypeRes toResponse(ContentType contentType) {
        return new CreateContentTypeRes(contentType);
    }
}
