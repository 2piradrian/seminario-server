package com.group3.catalog.domain.dto.moderationReason.mapper.implementation;

import com.group3.catalog.domain.dto.moderationReason.response.GetAllModerationReasonRes;
import com.group3.entity.ModerationReason;

import java.util.List;

public class GetAllModerationReasonMapper {

    public GetAllModerationReasonRes toResponse(List<ModerationReason> moderationReasons){
        return new GetAllModerationReasonRes(
                moderationReasons
        );
    }
}
