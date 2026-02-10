package com.group3.events.domain.dto.event.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetEventGrowthReportRes {

    private final Long yearlyCreatedEvents;

    private final Long weeklyCreatedEvents;

    private final Long monthlyCreatedEvents;

}
