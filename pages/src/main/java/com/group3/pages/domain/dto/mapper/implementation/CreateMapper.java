package com.group3.pages.domain.dto.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.Page;
import com.group3.entity.PageType;
import com.group3.entity.Style;
import com.group3.pages.domain.dto.request.CreatePageReq;
import com.group3.pages.domain.dto.response.CreatePageRes;

import java.util.List;
import java.util.Map;

public class CreateMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    public CreatePageReq toRequest(String token, Map<String, Object> payload){
        return CreatePageReq.create(
            token,
            (String) payload.get("name"),
            objectMapper.convertValue(payload.get("pageType"), new TypeReference<PageType>() {})
        );
    }

    public CreatePageRes toResponse(Page page){
        return new CreatePageRes(
                page.getId()
        );
    }
}
