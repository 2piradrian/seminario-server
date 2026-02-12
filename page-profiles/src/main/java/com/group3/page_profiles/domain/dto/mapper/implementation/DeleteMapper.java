package com.group3.page_profiles.domain.dto.mapper.implementation;

import com.group3.page_profiles.domain.dto.request.DeletePageReq;

import java.util.Map;

public class DeleteMapper {
    
    public DeletePageReq toRequest(String token, String pageId, Map<String, Object> payload) {
        return DeletePageReq.create(
            token,
            pageId,
                    (String) payload.get("reasonId")
        );
    }
    
}
