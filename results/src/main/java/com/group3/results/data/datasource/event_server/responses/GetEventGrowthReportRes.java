package com.group3.results.data.datasource.event_server.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetEventGrowthReportRes {
    private Long yearlyCreatedEvents;
    private Long monthlyCreatedEvents;
    private Long weeklyCreatedEvents;
}
