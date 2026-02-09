package com.group3.catalog.domain.dto.style.mapper.implementation;

import com.group3.catalog.domain.dto.style.request.EditStyleReq;
import com.group3.catalog.domain.dto.style.response.EditStyleRes;
import com.group3.entity.Style;

import java.util.Map;

public class EditMapper {

    public EditStyleReq toRequest(String token, String id, Map<String, Object> payload) {
        return EditStyleReq.create(
                token,
                id,
                (String) payload.get("name")
        );
    }

    public EditStyleRes toResponse(Style style) {
        return new EditStyleRes(style);
    }
}
