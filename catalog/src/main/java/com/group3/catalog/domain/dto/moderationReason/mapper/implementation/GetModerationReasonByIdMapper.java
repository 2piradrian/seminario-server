package com.group3.catalog.domain.dto.moderationReason.mapper.implementation;

import com.group3.catalog.domain.dto.moderationReason.request.GetModerationReasonByIdReq;
import com.group3.catalog.domain.dto.moderationReason.response.GetModerationReasonByIdRes;
import com.group3.entity.ModerationReason;

public class GetModerationReasonByIdMapper {

    public GetModerationReasonByIdReq toRequest(String id){
        return GetModerationReasonByIdReq.create(
                id
        );
    }

    public GetModerationReasonByIdRes toResponse(ModerationReason moderationReason){
        return new GetModerationReasonByIdRes(
                moderationReason
        );
    }
}
