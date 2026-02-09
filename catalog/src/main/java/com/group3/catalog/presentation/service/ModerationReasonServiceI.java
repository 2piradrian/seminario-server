package com.group3.catalog.presentation.service;

import com.group3.catalog.domain.dto.moderationReason.request.*;
import com.group3.catalog.domain.dto.moderationReason.response.*;

public interface ModerationReasonServiceI {

    GetAllModerationReasonRes getAll();

    GetModerationReasonByIdRes getById(GetModerationReasonByIdReq dto);

    CreateModerationReasonRes create(CreateModerationReasonReq dto);

    EditModerationReasonRes edit(EditModerationReasonReq dto);

    void delete(DeleteModerationReasonReq dto);
}
