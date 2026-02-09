package com.group3.catalog.domain.dto.moderationReason.response;

import com.group3.entity.ModerationReason;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetModerationReasonByIdRes {

    private final ModerationReason moderationReason;

}
