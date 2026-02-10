package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.entity.TimeReportContent;
import com.group3.events.domain.dto.event.request.GetEventGrowthReportReq;
import com.group3.events.domain.dto.event.response.GetEventGrowthReportRes;

public class GetEventGrowthReportMapper {

    public GetEventGrowthReportReq toRequest(String token, String secret) {
        return GetEventGrowthReportReq.create(
            token,
            secret
        );
    }

    public GetEventGrowthReportRes toResponse(TimeReportContent reportContent) {
        return new GetEventGrowthReportRes(
            reportContent.getYearlyReport(),
            reportContent.getWeeklyReport(),
            reportContent.getMonthlyReport()
        );
    }

}
