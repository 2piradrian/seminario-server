package com.group3.events.presentation.service;

import com.group3.events.domain.dto.event.request.*;
import com.group3.events.domain.dto.event.response.*;

public interface EventServiceI {

    CreateEventRes create(CreateEventReq dto);

    GetEventByIdRes getById(GetEventByIdReq dto);

    GetFilteredEventPageRes getFilteredEvents(GetFilteredEventPageReq dto);

    GetEventAndAssistsPageRes getEventsAndAssistsById(GetEventAndAssistsPageReq dto);

    GetOnlyPageEventPageRes getPageOnlyEventsPage(GetOnlyPageEventPageReq dto);

    GetEventByDateRangeRes getEventsByDateRange(GetEventByDateRangeReq dto);

    ToggleAssistRes toggleAssist(ToggleAssistReq dto);

    EditEventRes edit(EditEventReq dto);

    void delete(DeleteEventReq dto);

    void updateEventsLifeCycle();

}
