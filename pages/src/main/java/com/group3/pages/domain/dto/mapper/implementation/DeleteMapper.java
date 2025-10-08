package com.group3.pages.domain.dto.mapper.implementation;

import com.group3.pages.domain.dto.request.DeletePageReq;

import java.util.Map;

public class DeleteMapper {
    
    public DeletePageReq toRequest(String token, Map<String, Object> payload) {
        return DeletePageReq.create(
            token,
            (String) payload.get("pageId")
        );
    }
    
}
