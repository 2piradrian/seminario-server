package com.group3.catalog.domain.dto.moderationReason.mapper.implementation;

import com.group3.catalog.domain.dto.moderationReason.request.EditModerationReasonReq;
import com.group3.catalog.domain.dto.moderationReason.response.EditModerationReasonRes;
import com.group3.entity.ModerationReason;

import java.util.Map;

public class EditMapper {

    public EditModerationReasonReq toRequest(String token, String id, Map<String, Object> payload) {
        return EditModerationReasonReq.create(
                token,
                id,
                (String) payload.get("name")
        );
    }

    public EditModerationReasonRes toResponse(ModerationReason moderationReason) {
        return new EditModerationReasonRes(moderationReason);
    }
}
