package com.group3.catalog.domain.dto.moderationReason.mapper;

import com.group3.catalog.domain.dto.moderationReason.mapper.implementation.*;

public class ModerationReasonMapper {

    public static GetModerationReasonByIdMapper getById(){ return new GetModerationReasonByIdMapper(); }

    public static GetAllModerationReasonMapper getAll() { return new GetAllModerationReasonMapper(); }

    public static CreateMapper create() { return new CreateMapper(); }

    public static EditMapper edit() { return new EditMapper(); }

    public static DeleteMapper delete() { return new DeleteMapper(); }
}
