package com.group3.pages.domain.dto.mapper.implementation;

import com.group3.pages.domain.dto.request.DeletePageReq;

public class DeleteMapper {
    
    public DeletePageReq toRequest(String token, String pageId) {
        return DeletePageReq.create(
            token,
            pageId
        );
    }
    
}
