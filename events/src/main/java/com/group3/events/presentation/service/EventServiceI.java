package com.group3.events.presentation.service;

import com.group3.events.domain.dto.event.request.*;
import com.group3.events.domain.dto.event.response.*;

public interface EventServiceI {

    CreateEventRes create(CreateEventReq dto);

    GetEventByIdRes getById(GetEventByIdReq dto);

    GetOwnEventPageRes getOwnEvents(GetOwnEventPageReq dto);

    GetOwnEventsAssistedPageRes getOwnAsist(GetOwnEventsAssistedPageReq dto);

    EditEventRes edit(EditEventReq dto);

}
