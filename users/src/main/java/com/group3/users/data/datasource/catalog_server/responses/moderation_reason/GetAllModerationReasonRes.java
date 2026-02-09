package com.group3.users.data.datasource.catalog_server.responses.moderation_reason;

import com.group3.entity.ModerationReason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllModerationReasonRes {

    private List<ModerationReason> moderationReasons;

}
