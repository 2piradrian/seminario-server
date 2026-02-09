package com.group3.catalog.domain.dto.contentType.mapper.implementation;

import com.group3.catalog.domain.dto.contentType.request.EditContentTypeReq;
import com.group3.catalog.domain.dto.contentType.response.EditContentTypeRes;
import com.group3.entity.ContentType;

import java.util.Map;

public class EditMapper {

    public EditContentTypeReq toRequest(String token, String id, Map<String, Object> payload) {
        return EditContentTypeReq.create(
                token,
                id,
                (String) payload.get("name")
        );
    }

    public EditContentTypeRes toResponse(ContentType contentType) {
        return new EditContentTypeRes(contentType);
    }
}
