package com.group3.catalog.domain.dto.moderationReason.response;

import com.group3.entity.ModerationReason;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllModerationReasonRes {

    private final List<ModerationReason> moderationReasons;

}
