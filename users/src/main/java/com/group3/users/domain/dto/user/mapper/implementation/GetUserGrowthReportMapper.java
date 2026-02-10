package com.group3.users.domain.dto.user.mapper.implementation;

import com.group3.entity.TimeReportContent;
import com.group3.users.domain.dto.user.request.GetUserGrowthReportReq;
import com.group3.users.domain.dto.user.response.GetUserGrowthReportRes;

public class GetUserGrowthReportMapper {

    public GetUserGrowthReportReq toRequest(String token, String secret) {
        return GetUserGrowthReportReq.create(
            token,
            secret
        );
    }

    public GetUserGrowthReportRes toResponse(TimeReportContent reportContent) {
        return new GetUserGrowthReportRes(
            reportContent.getYearlyReport(),
            reportContent.getWeeklyReport(),
            reportContent.getMonthlyReport()
        );
    }

}