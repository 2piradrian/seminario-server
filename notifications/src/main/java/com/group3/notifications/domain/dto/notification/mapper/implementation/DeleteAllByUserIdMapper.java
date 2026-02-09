package com.group3.notifications.domain.dto.notification.mapper.implementation;

import com.group3.notifications.domain.dto.notification.request.DeleteAllByUserIdReq;

public class DeleteAllByUserIdMapper {

    public DeleteAllByUserIdReq toRequest(String secret, String userId) {
        return DeleteAllByUserIdReq.create(secret, userId);
    }
}
