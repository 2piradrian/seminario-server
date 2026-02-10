package com.group3.events.domain.dto.event.mapper;

import com.group3.events.domain.dto.event.mapper.implementation.*;

public class EventMapper {

    public static CreateMapper create() {
        return new CreateMapper();
    }

    public static GetByIdMapper getById() {
        return new GetByIdMapper();
    }

    public static GetEventAndAssistsMapper getEventAndAssistsMapper(){
        return new GetEventAndAssistsMapper();
    }

    public static GetOnlyPageEventMapper getOnlyPageEvents(){
        return new GetOnlyPageEventMapper();
    }

    public static EditMapper edit() {
        return new EditMapper();
    }

    public static CancelMapper cancel() {
        return new CancelMapper();
    }

    public static ToggleAssistMapper toggleAssist(){
        return new ToggleAssistMapper();
    }

    public static DeleteMapper delete() {
        return new DeleteMapper();
    }

    public static GetFilteredPageMapper getFilteredPage() {
        return new GetFilteredPageMapper();
    }

    public static GetEventByDateRangeMapper getEventByDateRange() {
        return new GetEventByDateRangeMapper();
    }

    public static GetAssistantsByEventIdMapper getAssistantsByEventId() { return new GetAssistantsByEventIdMapper(); }

    public static DeleteUserDataMapper deleteUserData() {
        return new DeleteUserDataMapper();
    }

    public static DeletePageEventsMapper deletePageEvents() {
        return new DeletePageEventsMapper();
    }

    public static GetEventGrowthReportMapper getEventGrowthReport() {
        return new GetEventGrowthReportMapper();
    }

}
