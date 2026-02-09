package com.group3.catalog.domain.dto.moderationReason.mapper.implementation;

import com.group3.catalog.domain.dto.moderationReason.request.CreateModerationReasonReq;
import com.group3.catalog.domain.dto.moderationReason.response.CreateModerationReasonRes;
import com.group3.entity.ModerationReason;

import java.util.Map;

public class CreateMapper {

    public CreateModerationReasonReq toRequest(String token, Map<String, Object> payload) {
        return CreateModerationReasonReq.create(
                token,
                (String) payload.get("name")
        );
    }

    public CreateModerationReasonRes toResponse(ModerationReason moderationReason) {
        return new CreateModerationReasonRes(moderationReason);
    }
}
