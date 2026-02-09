package com.group3.catalog.domain.dto.moderationReason.mapper.implementation;

import com.group3.catalog.domain.dto.moderationReason.request.DeleteModerationReasonReq;

public class DeleteMapper {

    public DeleteModerationReasonReq toRequest(String token, String id) {
        return DeleteModerationReasonReq.create(token, id);
    }
}
