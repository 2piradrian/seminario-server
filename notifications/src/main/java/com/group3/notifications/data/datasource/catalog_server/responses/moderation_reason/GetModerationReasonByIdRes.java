package com.group3.notifications.data.datasource.catalog_server.responses.moderation_reason;

import com.group3.entity.ModerationReason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetModerationReasonByIdRes {

    private ModerationReason moderationReason;

}
