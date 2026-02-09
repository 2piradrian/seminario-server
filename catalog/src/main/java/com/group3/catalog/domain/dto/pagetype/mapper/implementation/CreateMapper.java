package com.group3.catalog.domain.dto.pagetype.mapper.implementation;

import com.group3.catalog.domain.dto.pagetype.request.CreatePageTypeReq;
import com.group3.catalog.domain.dto.pagetype.response.CreatePageTypeRes;
import com.group3.entity.PageType;

import java.util.Map;

public class CreateMapper {

    public CreatePageTypeReq toRequest(String token, Map<String, Object> payload) {
        return CreatePageTypeReq.create(
                token,
                (String) payload.get("name")
        );
    }

    public CreatePageTypeRes toResponse(PageType pageType) {
        return new CreatePageTypeRes(pageType);
    }
}
