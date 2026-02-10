package com.group3.page_profiles.domain.dto.mapper.implementation;

import com.group3.entity.TimeReportContent;
import com.group3.page_profiles.domain.dto.request.GetPageGrowthReportReq;
import com.group3.page_profiles.domain.dto.response.GetPageGrowthReportRes;

public class GetPageGrowthReportMapper {

    public GetPageGrowthReportReq toRequest(String token, String secret) {
        return GetPageGrowthReportReq.create(
            token,
            secret
        );
    }

    public GetPageGrowthReportRes toResponse(TimeReportContent reportContent) {
        return new GetPageGrowthReportRes(
            reportContent.getYearlyReport(),
            reportContent.getWeeklyReport(),
            reportContent.getMonthlyReport()
        );
    }

}