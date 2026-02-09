package com.group3.catalog.domain.dto.pagetype.mapper.implementation;

import com.group3.catalog.domain.dto.pagetype.request.EditPageTypeReq;
import com.group3.catalog.domain.dto.pagetype.response.EditPageTypeRes;
import com.group3.entity.PageType;

import java.util.Map;

public class EditMapper {

    public EditPageTypeReq toRequest(String token, String id, Map<String, Object> payload) {
        return EditPageTypeReq.create(
                token,
                id,
                (String) payload.get("name")
        );
    }

    public EditPageTypeRes toResponse(PageType pageType) {
        return new EditPageTypeRes(pageType);
    }
}
