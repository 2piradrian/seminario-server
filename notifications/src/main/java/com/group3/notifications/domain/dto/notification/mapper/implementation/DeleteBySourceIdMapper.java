package com.group3.notifications.domain.dto.notification.mapper.implementation;

import com.group3.notifications.domain.dto.notification.request.DeleteBySourceIdReq;

public class DeleteBySourceIdMapper {

    public DeleteBySourceIdReq toRequest(String secret, String sourceId, String token) {
        return DeleteBySourceIdReq.create(secret, sourceId, token);
    }
}
