package com.group3.page_profiles.domain.dto.mapper.implementation;

import com.group3.page_profiles.domain.dto.request.DeleteUserPagesReq;

public class DeleteUserPagesMapper {
    
    public DeleteUserPagesReq toRequest(String userId, String secret) {
        return DeleteUserPagesReq.create(userId, secret);
    }

}
