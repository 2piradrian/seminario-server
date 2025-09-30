package com.group3.pages.domain.dto.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.PageType;
import com.group3.pages.domain.dto.request.EditPageReq;

import java.util.List;
import java.util.Map;

public class EditMapper {
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    public EditPageReq toRequest(String token, Map<String, Object> payload){
        return EditPageReq.create(
            token,
            (String) payload.get("name"),
            (String) payload.get("portraitImage"),
            (String) payload.get("profileImage"),
            (String) payload.get("shortDescription"),
            (String) payload.get("longDescription"),
            (String) payload.get("ownerId"),
            objectMapper.convertValue(payload.get("members"), new TypeReference<List<String>>() {}),
            objectMapper.convertValue(payload.get("pageType"), new TypeReference<PageType>() {})
        );
    }
    
}
