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

    GetAssistantsByEventIdRes getAssistantsByEventId(GetAssistantsByEventIdReq dto);

    ToggleAssistRes toggleAssist(ToggleAssistReq dto);

    EditEventRes edit(EditEventReq dto);

    void delete(DeleteEventReq dto);

    CancelEventRes cancel(CancelEventReq dto);

    void updateEventsLifeCycle();

    void deleteEventsByUserId(DeleteUserDataReq dto);

    void deleteEventsByPageId(DeletePageEventsReq dto);

}
