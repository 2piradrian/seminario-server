package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.events.domain.dto.event.request.DeleteUserDataReq;

public class DeleteUserDataMapper {

    public DeleteUserDataReq toRequest(String userId, String secret) {
        return DeleteUserDataReq.create(userId, secret);
    }
}
