package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.entity.TimeReportContent;
import com.group3.posts.domain.dto.post.request.GetPostGrowthReportReq;
import com.group3.posts.domain.dto.post.response.GetPostGrowthReportRes;


public class GetPostGrowthReportMapper {

    public GetPostGrowthReportReq toRequest(String token, String secret) {
        return GetPostGrowthReportReq.create(
            token,
            secret
        );
    }

    public GetPostGrowthReportRes toResponse(TimeReportContent reportContent) {
        return new GetPostGrowthReportRes(
            reportContent.getYearlyReport(),
            reportContent.getWeeklyReport(),
            reportContent.getMonthlyReport()
        );
    }

}
