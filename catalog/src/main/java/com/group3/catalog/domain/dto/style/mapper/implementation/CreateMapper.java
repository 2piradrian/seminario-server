package com.group3.catalog.domain.dto.style.mapper.implementation;

import com.group3.catalog.domain.dto.style.request.CreateStyleReq;
import com.group3.catalog.domain.dto.style.response.CreateStyleRes;
import com.group3.entity.Style;

import java.util.Map;

public class CreateMapper {

    public CreateStyleReq toRequest(String token, Map<String, Object> payload) {
        return CreateStyleReq.create(
                token,
                (String) payload.get("name")
        );
    }

    public CreateStyleRes toResponse(Style style) {
        return new CreateStyleRes(style);
    }
}
